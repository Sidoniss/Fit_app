package com.example.fit_app_bachelor.login.Service;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.fit_app_bachelor.login.model.User;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;
    private LoginDataSource dataSource;
    private UserManager userManager;

    private LoginRepository(LoginDataSource dataSource, UserManager userManager) {
        this.dataSource = dataSource;
        this.userManager = userManager;
    }

    public static LoginRepository getInstance(LoginDataSource dataSource,UserManager userManager) {
        if (instance == null) {
            instance = new LoginRepository(dataSource,userManager);
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

    public LiveData<Result<String>> register(String email, String password,String name) {
        return dataSource.register(email,password,name);
    }

    public LiveData<Result<String>> sendEmailWithToken(String email) {
        return dataSource.sendEmail(email);
    }

    public LiveData<Result<String>> resetPassword(String newPassword,String token) {
        return dataSource.resetPassword(newPassword, token);
    }

    public LiveData<Result<String>> changePassword(String oldPassword,String newPassword) {
        MutableLiveData<String> emailLiveData = new MutableLiveData<>();

        userManager.getUser(user ->  {
            if (user != null) {
                emailLiveData.postValue(user.getEmail());
            } else {
                emailLiveData.postValue(null);
            }
        });

        return Transformations.switchMap(emailLiveData, email -> {
            if(email != null) {
                return dataSource.changePassword(email,oldPassword,newPassword);
            } else {
                MutableLiveData<Result<String>> errorResult = new MutableLiveData<>();
                errorResult.setValue(new Result.Error(new IOException("No user logged in!")));
                return errorResult;
            }
        });
    }

}