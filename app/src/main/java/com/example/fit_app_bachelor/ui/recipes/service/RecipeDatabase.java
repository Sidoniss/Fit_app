package com.example.fit_app_bachelor.ui.recipes.service;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.example.fit_app_bachelor.ui.recipes.model.Recipe;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Recipe.class}, version = 2, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class RecipeDatabase extends RoomDatabase {
    private static RecipeDatabase instance;
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
                String json = new String(buffer, StandardCharsets.UTF_8);

                Gson gson = new Gson();
                Recipe[] recipes = gson.fromJson(json,Recipe[].class);
                recipeDAO().insertAll(recipes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        },executorService);

    }
}
