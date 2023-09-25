package com.example.fit_app_bachelor.ui.recipes.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

@Entity(tableName = "recipes")
public class Recipe implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private int time_of_cook;
    private String description;

    private List<Ingredient> ingredients;
    private int kcal;
    private String picture;
    private HashMap<String,Boolean> filters;

    public Recipe(String title, int time_of_cook, String description,List<Ingredient> ingredients, int kcal, String picture,HashMap<String,Boolean> filters) {
        this.title = title;
        this.time_of_cook = time_of_cook;
        this.description = description;
        this.ingredients = ingredients;
        this.kcal = kcal;
        this.picture = picture;
        this.filters = filters;
    }

    public Recipe() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTime_of_cook() {
        return time_of_cook;
    }

    public void setTime_of_cook(int time_of_cook) {
        this.time_of_cook = time_of_cook;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public HashMap<String, Boolean> getFilters() {
        return filters;
    }

    public void setFilters(HashMap<String, Boolean> filters) {
        this.filters = filters;
    }
}
