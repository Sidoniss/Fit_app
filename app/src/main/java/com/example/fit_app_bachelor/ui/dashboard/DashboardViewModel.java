package com.example.fit_app_bachelor.ui.dashboard;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fit_app_bachelor.ui.dashboard.model.Recipe;
import com.example.fit_app_bachelor.ui.dashboard.service.RecipeDAO;
import com.example.fit_app_bachelor.ui.dashboard.service.RecipeDatabase;

import java.io.Closeable;
import java.util.List;

public class DashboardViewModel extends ViewModel {

    private final RecipeDAO recipeDAO;
    private final LiveData<List<Recipe>> recipes;

//    public DashboardViewModel(RecipeDAO recipeDAO) {
//        this.recipeDAO = recipeDAO;
//        mText = new MutableLiveData<>();
//        mText.setValue("This is dashboard fragment");
//    }


    public DashboardViewModel(@NonNull Application application) {
        super();
        this.recipeDAO = RecipeDatabase.getInstance(application).recipeDAO();
        recipes = recipeDAO.getAllRecipes();
    }

    public LiveData<List<Recipe>> getRecipes() {
        return recipes;
    }
}