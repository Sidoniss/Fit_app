package com.example.fit_app_bachelor.ui.recipes.service;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.fit_app_bachelor.ui.recipes.model.Recipe;

import java.util.List;

@Dao
public interface RecipeDAO {
    @Insert
    void insertRecipe(Recipe recipe);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Recipe[] recipes);

    @Query("SELECT * FROM recipes")
    LiveData<List<Recipe>> getAllRecipes();
}
