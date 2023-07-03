package com.example.fit_app_bachelor.login.Service;

import com.example.fit_app_bachelor.login.model.LoginRequest;
import com.example.fit_app_bachelor.login.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("signin")
    Call<LoginResponse> login(@Body LoginRequest request);
}

