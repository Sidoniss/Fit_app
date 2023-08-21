package com.example.fit_app_bachelor.login.Service;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.fit_app_bachelor.login.model.User;

@Database(entities = {User.class},version = 1)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDAO userDAO();

    private static volatile UserDatabase INSTANCE;

    public static UserDatabase getInstance(Context context) {
        if(INSTANCE == null) {
            synchronized (UserDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            UserDatabase.class,"UserDB")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
