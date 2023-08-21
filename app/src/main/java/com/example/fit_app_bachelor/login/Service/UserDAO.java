package com.example.fit_app_bachelor.login.Service;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.fit_app_bachelor.login.model.User;

@Dao
public interface UserDAO {
    @Insert
    void insert(User user);

    @Query("SELECT * FROM user LIMIT 1")
    User getUser();

    @Query("DELETE FROM user")
    void deleteUser();
}
