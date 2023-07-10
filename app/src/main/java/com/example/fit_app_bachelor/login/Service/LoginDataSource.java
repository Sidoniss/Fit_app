package com.example.fit_app_bachelor.login.Service;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fit_app_bachelor.login.model.LoginRequest;
import com.example.fit_app_bachelor.login.model.LoginResponse;
import com.example.fit_app_bachelor.login.model.RegisterRequest;
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

    public LoginDataSource(ApiService apiService) {
        this.apiService = apiService;
        this.resultMutableLiveData = new MutableLiveData<>();
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

        Call<LoginResponse> call = apiService.register(request);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    if(loginResponse != null) {
                        User user = new User(loginResponse.getEmail());
                        resultMutableLiveData.postValue(new Result.Success<>(user));
                    } else {
                        resultMutableLiveData.postValue(new Result.Error(new IOException("Invalid register data!")));
                    }
                }
                else {
                    resultMutableLiveData.postValue(new Result.Error(new IOException("Invalid register data!")));
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                resultMutableLiveData.postValue(new Result.Error(new IOException("Registration error!")));
            }
        });

        return resultMutableLiveData;
    }

    public void logout() {
        // TODO: revoke authentication
    }
}