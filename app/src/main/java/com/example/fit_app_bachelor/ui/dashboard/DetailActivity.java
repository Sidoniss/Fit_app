package com.example.fit_app_bachelor.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fit_app_bachelor.R;
import com.example.fit_app_bachelor.ui.dashboard.model.Recipe;
import com.example.fit_app_bachelor.ui.dashboard.service.IngredientAdapter;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private TextView titleTextView;
    private TextView timeOfCookTextView;
    private TextView kcalTextView;
    private RecyclerView ingredientsRecyclerView;
    private TextView descriptionTextView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        titleTextView = findViewById(R.id.titleTextViewAC);
        timeOfCookTextView = findViewById(R.id.timeOfCookTextViewAC);
        kcalTextView = findViewById(R.id.kcalTextViewAC);
        ingredientsRecyclerView = findViewById(R.id.ingredients_recyclerview);
        descriptionTextView = findViewById(R.id.descriptionTextViewAC);
        imageView = findViewById(R.id.recipeImageAC);

        Intent intent = getIntent();
        Recipe recipe = (Recipe) intent.getSerializableExtra("recipe");

        titleTextView.setText(recipe.getTitle());
        timeOfCookTextView.setText(String.valueOf("time: " + recipe.getTime_of_cook()));
        descriptionTextView.setText(recipe.getDescription());
        kcalTextView.setText(String.valueOf("kcal: " + recipe.getKcal()));
        Picasso.get().load(recipe.getPicture()).into(imageView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        ingredientsRecyclerView.setLayoutManager(layoutManager);

        IngredientAdapter ingredientAdapter = new IngredientAdapter(recipe.getIngredients());
        ingredientsRecyclerView.setAdapter(ingredientAdapter);

    }
}