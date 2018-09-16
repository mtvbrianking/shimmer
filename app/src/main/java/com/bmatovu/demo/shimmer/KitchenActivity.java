package com.bmatovu.demo.shimmer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.bmatovu.demo.shimmer.http.Api;
import com.bmatovu.demo.shimmer.http.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KitchenActivity extends AppCompatActivity {

   private static final String TAG = Kitchen.class.getSimpleName();

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_kitchen);

      loadRecipes();
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
               Toast.makeText(KitchenActivity.this, recipes.toString(), Toast.LENGTH_LONG).show();
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
