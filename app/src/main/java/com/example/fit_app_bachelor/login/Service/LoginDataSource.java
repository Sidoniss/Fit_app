package com.example.fit_app_bachelor.login.Service;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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
    private MutableLiveData<Result<User>> resultMutableLiveData;
    private MutableLiveData<Result<String>> emailresultMutableLiveData;
    private MutableLiveData<Result<String>> resetresultMutableLiveData;

    public LoginDataSource(ApiService apiService) {
        this.apiService = apiService;
        this.resultMutableLiveData = new MutableLiveData<>();
        this.emailresultMutableLiveData = new MutableLiveData<>();
        this.resetresultMutableLiveData = new MutableLiveData<>();
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
                        User user = new User(loginResponse.getEmail());
                        resultMutableLiveData.postValue(new Result.Success<>(user));
                    } else {
                        resultMutableLiveData.postValue(new Result.Error(new IOException("Invalid login data!")));
                    }
                }
                else {
                    resultMutableLiveData.postValue(new Result.Error(new IOException("Invalid login data!")));
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                resultMutableLiveData.postValue(new Result.Error(new IOException("Error logging in!")));
            }
        });

        return resultMutableLiveData;
    }

    public LiveData<Result<User>> register(String username, String password, String name) {

        RegisterRequest request = new RegisterRequest(username,password,name);

        Call<Void> call = apiService.register(request);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()) {
                    User user = new User(username);
                    resultMutableLiveData.postValue(new Result.Success<>(user));
                } else {
                    resultMutableLiveData.postValue(new Result.Error(new IOException("Invalid register data!")));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                resultMutableLiveData.postValue(new Result.Error(new IOException("Registration error!")));
            }
        });

        return resultMutableLiveData;
    }

    public LiveData<Result<String>> sendEmail(String email) {
        RecoverRequest request = new RecoverRequest(email);

        Call<Void> call = apiService.sendEmailWithToken(request);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    emailresultMutableLiveData.postValue(new Result.Success<>("Email with reset token sent."));
                } else {
                    emailresultMutableLiveData.postValue(new Result.Error(new IOException("Error sending email with token!")));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                emailresultMutableLiveData.postValue(new Result.Error(new IOException("Error sending email with token!")));
            }
        });

        return emailresultMutableLiveData;
    }

    public LiveData<Result<String>> resetPassword(String newPassword,String token) {
        ResetRequest request = new ResetRequest(newPassword,token);


        Call<Void> call = apiService.resetPassword(request);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    resetresultMutableLiveData.postValue(new Result.Success<>("Password has been reset."));
                } else {
                    resetresultMutableLiveData.postValue(new Result.Error(new IOException("Error resetting password!")));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                resetresultMutableLiveData.postValue(new Result.Error(new IOException("Error resetting password!")));
            }
        });

        return resetresultMutableLiveData;
    }

    public void logout() {
        // TODO: revoke authentication
    }
}