package com.bmatovu.demo.shimmer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bmatovu.demo.shimmer.http.Api;
import com.bmatovu.demo.shimmer.http.Recipe;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KitchenActivity extends AppCompatActivity {

   private static final String TAG = Kitchen.class.getSimpleName();

   private RecyclerView recyclerView;
   private List<Recipe> recipeList;
   private RecipeListAdapter recipeListAdapter;

   private ShimmerFrameLayout shimmerContainer;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_kitchen);

      shimmerContainer = findViewById(R.id.shimmer_view_container);

      recyclerView = findViewById(R.id.recycler_view);
      recipeList = new ArrayList<>();
      recipeListAdapter = new RecipeListAdapter(this, recipeList);

      RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
      recyclerView.setLayoutManager(mLayoutManager);
      recyclerView.setItemAnimator(new DefaultItemAnimator());
      recyclerView.setAdapter(recipeListAdapter);

      loadRecipes();
   }

   @Override
   public void onResume() {
      super.onResume();
      shimmerContainer.startShimmer();
   }

   @Override
   public void onPause() {
      shimmerContainer.stopShimmer();
      super.onPause();
   }

   private void loadRecipes() {

      Api api = ((Kitchen) getApplication()).getRetrofit().create(Api.class);

      Call<List<Recipe>> call = api.getRecipes("https://api.androidhive.info/json/shimmer/menu.php");

      call.enqueue(new Callback<List<Recipe>>() {
         @Override
         public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
            if (response.isSuccessful()) {
               List<Recipe> recipes = response.body();
               Log.d(TAG, recipes.toString());
               // Toast.makeText(KitchenActivity.this, recipes.toString(), Toast.LENGTH_LONG).show();

               // adding recipes to recipe list
               recipeList.clear();
               recipeList.addAll(recipes);

               // refreshing recycler view
               recipeListAdapter.notifyDataSetChanged();

               // stop animating Shimmer and hide the layout
               shimmerContainer.stopShimmer();
               shimmerContainer.setVisibility(View.GONE);
            } else {
               Toast.makeText(KitchenActivity.this, "Not successful :|", Toast.LENGTH_LONG).show();
            }
         }

         @Override
         public void onFailure(Call<List<Recipe>> call, Throwable t) {
            Toast.makeText(KitchenActivity.this, "Failed :(", Toast.LENGTH_LONG).show();
         }
      });
   }
}
