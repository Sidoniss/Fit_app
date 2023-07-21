package com.example.fit_app_bachelor.ui.dashboard.service;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fit_app_bachelor.R;
import com.example.fit_app_bachelor.ui.dashboard.DetailActivity;
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
        return new RecipeViewHolder(itemView, this);
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

    public void onRecipeClick(int position,View view) {
        Recipe recipe = recipes.get(position);
        Intent intent = new Intent(view.getContext(), DetailActivity.class);
        intent.putExtra("recipe",recipe);
        view.getContext().startActivity(intent);
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView titleTextView;
        private TextView timeOfCookTextView;
        private TextView kcalTextView;
        private ImageView imageView;
        private CardView cardView;
        private RecipeAdapter listener;

        public RecipeViewHolder(@NonNull View itemView,RecipeAdapter listener) {
            super(itemView);
            titleTextView = itemView.findViewById( R.id.titleTextView);
            timeOfCookTextView = itemView.findViewById(R.id.timeOfCookTextView);
            kcalTextView = itemView.findViewById(R.id.kcalTextView);
            imageView = itemView.findViewById(R.id.recipeImage);
            cardView = itemView.findViewById(R.id.cardView);
            cardView.setOnClickListener(this);
            this.listener = listener;
        }

        public void bindData(Recipe recipe) {
            titleTextView.setText(recipe.getTitle());
            timeOfCookTextView.setText(String.valueOf("time: " + recipe.getTime_of_cook()));
            kcalTextView.setText(String.valueOf("kcal: " + recipe.getKcal()));
            Picasso.get().load(recipe.getPicture()).into(imageView);
        }


        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION) {
                listener.onRecipeClick(position,view);
            }
        }
    }

    public void filterList(List<Recipe> filteredRecipes) {
        this.recipes = filteredRecipes;
        notifyDataSetChanged();
    }
}
