package com.example.fit_app_bachelor.ui.dashboard.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipes")
public class Recipe {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private int time_of_cook;
    private String description;
    private int kcal;
    private String picture;

    public Recipe(String title, int time_of_cook, String description, int kcal, String picture) {
        this.title = title;
        this.time_of_cook = time_of_cook;
        this.description = description;
        this.kcal = kcal;
        this.picture = picture;
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
}
