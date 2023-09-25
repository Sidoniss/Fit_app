package com.example.fit_app_bachelor.ui.trainings.service;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.fit_app_bachelor.ui.trainings.model.Training;

import java.util.List;

@Dao
public interface TrainingDAO {
    @Insert
    void insertTrending(Training training);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Training[] trainings);

    @Query("SELECT * FROM trainings")
    LiveData<List<Training>> getAllTrainings();

    @Query("SELECT * FROM trainings WHERE category = :category")
    LiveData<List<Training>> getTrainingsByCategory(int category);
}
