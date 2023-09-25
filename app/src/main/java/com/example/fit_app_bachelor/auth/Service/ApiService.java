package com.example.fit_app_bachelor.auth.Service;

import com.example.fit_app_bachelor.auth.model.ChangePasswordRequest;
import com.example.fit_app_bachelor.auth.model.LoginRequest;
import com.example.fit_app_bachelor.auth.model.LoginResponse;
import com.example.fit_app_bachelor.auth.model.RecoverRequest;
import com.example.fit_app_bachelor.auth.model.RegisterRequest;
import com.example.fit_app_bachelor.auth.model.ResetRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("signin")
    Call<LoginResponse> login(@Body LoginRequest request);

    @POST("signup")
    Call<Void> register(@Body RegisterRequest request);

    @POST("request")
    Call<Void> sendEmailWithToken(@Body RecoverRequest request);

    @POST("reset")
    Call<Void> resetPassword(@Body ResetRequest request);

    @POST("change-password")
    Call<Void> changePassword(@Body ChangePasswordRequest request);
}

