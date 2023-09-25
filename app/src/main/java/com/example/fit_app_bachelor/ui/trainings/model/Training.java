package com.example.fit_app_bachelor.ui.trainings.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "trainings")
public class Training implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private int time;
    private int category;

    private int difficult;
    private List<Stage> stageList;

    public Training() {
    }

    public Training(String title, int time, int category, int difficult, List<Stage> stageList) {
        this.title = title;
        this.time = time;
        this.category = category;
        this.difficult = difficult;
        this.stageList = stageList;
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getDifficult() {
        return difficult;
    }

    public void setDifficult(int difficult) {
        this.difficult = difficult;
    }

    public List<Stage> getStageList() {
        return stageList;
    }

    public void setStageList(List<Stage> stageList) {
        this.stageList = stageList;
    }
}
