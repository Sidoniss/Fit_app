package com.example.fit_app_bachelor.login.Service;

import androidx.lifecycle.LiveData;

import com.example.fit_app_bachelor.login.model.User;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;
    private LoginDataSource dataSource;

    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }

    /*public boolean isLoggedIn() {
        return dataSource.isLoggedIn();
    }*/

    public void logout() {
        dataSource.logout();
    }

    public LiveData<Result<User>> login(String email, String password) {
        return dataSource.login(email,password);
    }

    public LiveData<Result<User>> register(String email, String password,String name) {
        return dataSource.register(email,password,name);
    }
}