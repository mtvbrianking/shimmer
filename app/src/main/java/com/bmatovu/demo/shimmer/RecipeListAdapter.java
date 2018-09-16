package com.bmatovu.demo.shimmer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bmatovu.demo.shimmer.http.Recipe;
import com.bumptech.glide.Glide;

import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.MyViewHolder> {

   private Context context;
   private List<Recipe> recipes;

   public class MyViewHolder extends RecyclerView.ViewHolder {
      public TextView name, description, price, chef, timestamp;
      public ImageView thumbnail;

      public MyViewHolder(View view) {
         super(view);
         name = view.findViewById(R.id.name);
         chef = view.findViewById(R.id.chef);
         description = view.findViewById(R.id.description);
         price = view.findViewById(R.id.price);
         thumbnail = view.findViewById(R.id.thumbnail);
         timestamp = view.findViewById(R.id.timestamp);
      }
   }


   public RecipeListAdapter(Context context, List<Recipe> recipes) {
      this.context = context;
      this.recipes = recipes;
   }

   @Override
   public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View itemView = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.recipe_list_item, parent, false);

      return new MyViewHolder(itemView);
   }

   @Override
   public void onBindViewHolder(MyViewHolder holder, final int position) {
      final Recipe recipe = recipes.get(position);
      holder.name.setText(recipe.getName());
      holder.chef.setText("By " + recipe.getChef());
      holder.description.setText(recipe.getDescription());
      holder.price.setText("Price: $" + recipe.getPrice());
      holder.timestamp.setText(recipe.getTimestamp());

      Glide.with(context)
            .load(recipe.getThumbnail())
            .into(holder.thumbnail);
   }

   // recipe
   @Override
   public int getItemCount() {
      return recipes.size();
   }

}
