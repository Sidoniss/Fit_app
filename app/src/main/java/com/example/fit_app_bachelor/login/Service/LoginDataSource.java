package com.example.fit_app_bachelor.login.Service;

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

    public LoginDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    public Result<User> login(String username, String password) {
        final Result<User>[] result = new Result[]{null};

        LoginRequest request = new LoginRequest(username,password);

        Call<LoginResponse> call = apiService.login(request);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    if(loginResponse != null) {
                        User user = loginResponse.getUser();
                        result[0] = new Result.Error(new IOException("Invalid login data!"));
                    } else {
                        result[0] = new Result.Error(new IOException("Invalid login data!"));
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                result[0] = new Result.Error(new IOException("Error logging in!", t));
            }
        });

        try {
            synchronized (result) {
                result.wait();
            }
        } catch (InterruptedException e ) {
            e.printStackTrace();
        }

        return result[0];
    }

    public void logout() {
        // TODO: revoke authentication
    }
}