package com.example.fit_app_bachelor.ui.home.model;

import java.io.Serializable;

public class Stage implements Serializable {
    private String description;
    private String imageUrl;
    private int time;
    private int type;

    public Stage(String description, String imageUrl, int time,int type) {
        this.description = description;
        this.imageUrl = imageUrl;
        this.time = time;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
