package com.example.fit_app_bachelor.ui.trainings.service;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.fit_app_bachelor.ui.trainings.model.Training;
import com.google.gson.Gson;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Training.class},version = 1,exportSchema = false)
@TypeConverters(TrainingConverters.class)
public abstract class TrainingDatabase extends RoomDatabase {

    private static TrainingDatabase instance;
    private final MutableLiveData<Boolean> mlsDatabaseCreated = new MutableLiveData<>();
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public abstract TrainingDAO trainingDAO();

    public static synchronized TrainingDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TrainingDatabase.class,"trainingDatabase.db")
                    .addCallback(new Callback() {
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
                InputStream is = context.getAssets().open("trainings.JSON");

                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);

                is.close();
                String json = new String(buffer, StandardCharsets.UTF_8);

                Gson gson = new Gson();
                Training[] trainings = gson.fromJson(json, Training[].class);
                trainingDAO().insertAll(trainings);

            } catch (Exception e) {
                Log.e("TrainingDatabase", "Error populating the database", e);
            }
        },executorService);
    }
}
