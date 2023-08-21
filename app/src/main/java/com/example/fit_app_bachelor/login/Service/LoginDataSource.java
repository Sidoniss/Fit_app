package com.example.fit_app_bachelor.login.Service;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fit_app_bachelor.MyApp;
import com.example.fit_app_bachelor.login.model.ChangePasswordRequest;
import com.example.fit_app_bachelor.login.model.LoginRequest;
import com.example.fit_app_bachelor.login.model.LoginResponse;
import com.example.fit_app_bachelor.login.model.RecoverRequest;
import com.example.fit_app_bachelor.login.model.RegisterRequest;
import com.example.fit_app_bachelor.login.model.ResetRequest;
import com.example.fit_app_bachelor.login.model.User;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    private ApiService apiService;
    private UserManager userManager;
    private MutableLiveData<Result<User>> loginResultMutableLiveData;
    private MutableLiveData<Result<String>> registerResultMutableLiveData;
    private MutableLiveData<Result<String>> emailResultMutableLiveData;
    private MutableLiveData<Result<String>> resetResultMutableLiveData;
    private MutableLiveData<Result<String>> changePasswordMutableLiveData;
    private PreferencesHelper preferencesHelper;

    public LoginDataSource(ApiService apiService, UserManager userManager) {
        this.apiService = apiService;
        this.loginResultMutableLiveData = new MutableLiveData<>();
        this.registerResultMutableLiveData = new MutableLiveData<>();
        this.emailResultMutableLiveData = new MutableLiveData<>();
        this.resetResultMutableLiveData = new MutableLiveData<>();
        this.changePasswordMutableLiveData = new MutableLiveData<>();
        this.preferencesHelper = PreferencesHelper.getInstance();
        this.userManager = userManager;
    }

    public LiveData<Result<User>> login(String username, String password) {

        LoginRequest request = new LoginRequest(username,password);

        Call<LoginResponse> call = apiService.login(request);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    if(loginResponse != null) {
                        User user = new User(loginResponse.getEmail(), loginResponse.getName(), loginResponse.getAccountCreationDate());
                        userManager.getUser(u -> userManager.insertUser(user));
                        loginResultMutableLiveData.postValue(new Result.Success<>(user));
                        preferencesHelper.setLoggedIn(true);
                    } else {
                        loginResultMutableLiveData.postValue(new Result.Error(new IOException("Invalid login data!")));
                    }
                }
                else {
                    loginResultMutableLiveData.postValue(new Result.Error(new IOException("Invalid login data!")));
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginResultMutableLiveData.postValue(new Result.Error(new IOException("Error logging in!")));
            }
        });

        return loginResultMutableLiveData;
    }

    public LiveData<Result<String>> register(String username, String password, String name) {

        RegisterRequest request = new RegisterRequest(username,password,name);

        Call<Void> call = apiService.register(request);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()) {
                    registerResultMutableLiveData.postValue(new Result.Success<>("Register successful."));
                } else {
                    registerResultMutableLiveData.postValue(new Result.Error(new IOException("Invalid register data!")));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                registerResultMutableLiveData.postValue(new Result.Error(new IOException("Registration error!")));
            }
        });

        return registerResultMutableLiveData;
    }

    public LiveData<Result<String>> sendEmail(String email) {
        RecoverRequest request = new RecoverRequest(email);

        Call<Void> call = apiService.sendEmailWithToken(request);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    emailResultMutableLiveData.postValue(new Result.Success<>("Email with reset token sent."));
                } else {
                    emailResultMutableLiveData.postValue(new Result.Error(new IOException("Error sending email with token!")));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                emailResultMutableLiveData.postValue(new Result.Error(new IOException("Error sending email with token!")));
            }
        });

        return emailResultMutableLiveData;
    }

    public LiveData<Result<String>> resetPassword(String newPassword,String token) {
        ResetRequest request = new ResetRequest(newPassword,token);


        Call<Void> call = apiService.resetPassword(request);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    resetResultMutableLiveData.postValue(new Result.Success<>("Password has been reset."));
                } else {
                    resetResultMutableLiveData.postValue(new Result.Error(new IOException("Error resetting password!")));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                resetResultMutableLiveData.postValue(new Result.Error(new IOException("Error resetting password!")));
            }
        });

        return resetResultMutableLiveData;
    }

    public LiveData<Result<String>> changePassword(String email,String oldPassword,String newPassword) {
        ChangePasswordRequest request = new ChangePasswordRequest(email, oldPassword, newPassword);

        Call<Void> call = apiService.changePassword(request);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    changePasswordMutableLiveData.postValue(new Result.Success<>("Password has been changed."));
                } else {
                    changePasswordMutableLiveData.postValue(new Result.Error(new IOException("Error changing password")));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                changePasswordMutableLiveData.postValue(new Result.Error(new IOException("Error changing password")));
            }
        });

        return changePasswordMutableLiveData;
    }

    public void logout() {
        preferencesHelper.setLoggedIn(false);
        userManager.deleteUser();
    }
}