package com.example.fit_app_bachelor.data.LoginService;

import com.example.fit_app_bachelor.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    private UserDAO userDAO;

    public LoginDataSource(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Result<LoggedInUser> login(String username, String password) {
        final Result<LoggedInUser>[] result = new Result[]{null};

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    LoggedInUser loggedInUser = UserDAO.authenticate(username, password);
                    if (loggedInUser != null) {
                        result[0] = new Result.Success<>(loggedInUser);
                    } else {
                        result[0] = new Result.Error(new IOException("Invalid login data!"));
                    }
                } catch (Exception e) {
                    result[0] = new Result.Error(new IOException("Error logging in", e));
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result[0];
    }

    public void logout() {
        // TODO: revoke authentication
    }
}