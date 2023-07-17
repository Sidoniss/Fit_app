package com.example.fit_app_bachelor.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fit_app_bachelor.R;
import com.example.fit_app_bachelor.ui.dashboard.model.Recipe;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private TextView titleTextView;
    private TextView timeOfCookTextView;
    private TextView kcalTextView;
    private TextView descriptionTextView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        titleTextView = findViewById(R.id.titleTextViewAC);
        timeOfCookTextView = findViewById(R.id.timeOfCookTextViewAC);
        kcalTextView = findViewById(R.id.kcalTextViewAC);
        descriptionTextView = findViewById(R.id.descriptionTextViewAC);
        imageView = findViewById(R.id.recipeImageAC);

        Intent intent = getIntent();
        Recipe recipe = (Recipe) intent.getSerializableExtra("recipe");

        titleTextView.setText(recipe.getTitle());
        timeOfCookTextView.setText(String.valueOf("time: " + recipe.getTime_of_cook()));
        descriptionTextView.setText(recipe.getDescription());
        kcalTextView.setText(String.valueOf("kcal: " + recipe.getKcal()));
        Picasso.get().load(recipe.getPicture()).into(imageView);

    }



}