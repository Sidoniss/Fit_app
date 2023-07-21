package com.example.fit_app_bachelor.ui.dashboard.service;

import androidx.room.TypeConverter;

import com.example.fit_app_bachelor.ui.dashboard.model.Ingredient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class Converters {
    @TypeConverter
    public static List<Ingredient> fromString(String value) {
        Type listType = new TypeToken<List<Ingredient>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromList(List<Ingredient> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    @TypeConverter
    public static HashMap<String, Boolean> fromStringToMap(String value) {
        Type mapType = new TypeToken<HashMap<String, Boolean>>() {}.getType();
        return new Gson().fromJson(value, mapType);
    }

    @TypeConverter
    public static String fromMapToString(HashMap<String, Boolean> map) {
        Gson gson = new Gson();
        String json = gson.toJson(map);
        return json;
    }
}
