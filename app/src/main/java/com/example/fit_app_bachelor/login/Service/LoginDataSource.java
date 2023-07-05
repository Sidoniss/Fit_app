package com.example.fit_app_bachelor.login.Service;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.fit_app_bachelor.login.activities.LoginViewModel;
import com.example.fit_app_bachelor.login.activities.LoginViewModelFactory;
import com.example.fit_app_bachelor.login.model.LoginRequest;
import com.example.fit_app_bachelor.login.model.LoginResponse;
import com.example.fit_app_bachelor.login.model.User;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
                        User user = loginResponse.getUser();
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

    public void logout() {
        // TODO: revoke authentication
    }
}