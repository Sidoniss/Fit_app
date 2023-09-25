package com.example.fit_app_bachelor.auth.Service;

import android.content.Context;

import com.example.fit_app_bachelor.auth.model.User;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class UserManager {
    private UserDAO userDAO;
    private Executor executor = Executors.newSingleThreadExecutor();

    public UserManager(Context context) {
        UserDatabase db = UserDatabase.getInstance(context);
        userDAO = db.userDAO();
    }

    public void getUser(Consumer<User> callback) {
        executor.execute(() -> {
            User user = userDAO.getUser();
            callback.accept(user);
        });
    }

    public void insertUser(User user) {
        executor.execute(() -> {
            userDAO.insert(user);
        });
    }

    public void deleteUser() {
        executor.execute(() -> {
            userDAO.deleteUser();
        });
    }
}
