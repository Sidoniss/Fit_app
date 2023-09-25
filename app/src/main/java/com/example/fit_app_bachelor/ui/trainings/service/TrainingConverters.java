package com.example.fit_app_bachelor.ui.trainings.service;

import androidx.room.TypeConverter;

import com.example.fit_app_bachelor.ui.trainings.model.Stage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class TrainingConverters {
    @TypeConverter
    public static List<Stage> fromString(String value) {
        Type listType = new TypeToken<List<Stage>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromList(List<Stage> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
