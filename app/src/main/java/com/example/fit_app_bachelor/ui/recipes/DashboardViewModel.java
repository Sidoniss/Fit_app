package com.example.fit_app_bachelor.ui.recipes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.fit_app_bachelor.ui.recipes.model.Recipe;
import com.example.fit_app_bachelor.ui.recipes.service.RecipeDAO;
import com.example.fit_app_bachelor.ui.recipes.service.RecipeDatabase;

import java.util.List;

public class DashboardViewModel extends ViewModel {

    private final RecipeDAO recipeDAO;
    private final LiveData<List<Recipe>> recipes;

    public DashboardViewModel(@NonNull Application application) {
        super();
        this.recipeDAO = RecipeDatabase.getInstance(application).recipeDAO();
        recipes = recipeDAO.getAllRecipes();
    }

    public LiveData<List<Recipe>> getRecipes() {
        return recipes;
    }
}