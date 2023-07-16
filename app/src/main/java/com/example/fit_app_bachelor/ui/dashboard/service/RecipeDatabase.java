package com.example.fit_app_bachelor.ui.dashboard.service;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.fit_app_bachelor.ui.dashboard.model.Recipe;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Recipe.class}, version = 1, exportSchema = false)
public abstract class RecipeDatabase extends RoomDatabase {
    private static RecipeDatabase instance;
    private final MutableLiveData<Boolean> mLsDatabaseCreated = new MutableLiveData<>();
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public abstract RecipeDAO recipeDAO();

    public static synchronized RecipeDatabase getInstance(Context context) {
        if (instance ==null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    RecipeDatabase.class,"recipesDatabase.db")
                    .addCallback(new RoomDatabase.Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            instance.populateDbAsync(context);
                        }
                    })
                    .build();
        }
        return instance;
    }

    private void populateDbAsync(final Context context) {
        CompletableFuture.runAsync(() -> {
            try {
                InputStream is = context.getAssets().open("recipes.JSON");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                String json = new String(buffer,"UTF-8");

                Gson gson = new Gson();
                Recipe[] recipes = gson.fromJson(json,Recipe[].class);
                recipeDAO().insertAll(recipes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        },executorService);
    }
}
