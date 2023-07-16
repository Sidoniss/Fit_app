package com.example.fit_app_bachelor.ui.dashboard.service;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fit_app_bachelor.R;
import com.example.fit_app_bachelor.ui.dashboard.model.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    private List<Recipe> recipes;

    public RecipeAdapter(List<Recipe> recipes) {
        this.recipes = recipes;
    }


    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item,parent,false);
        return new RecipeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);


        holder.bindData(recipe);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView timeOfCookTextView;
        private TextView descriptionTextView;
        private ImageView imageView;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            timeOfCookTextView = itemView.findViewById(R.id.timeOfCookTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            imageView = itemView.findViewById(R.id.recipeImage);
        }

        public void bindData(Recipe recipe) {
            titleTextView.setText(recipe.getTitle());
            timeOfCookTextView.setText(String.valueOf(recipe.getTime_of_cook()));
            descriptionTextView.setText(recipe.getDescription());
            Picasso.get().load(recipe.getPicture()).into(imageView);
        }
    }
}
