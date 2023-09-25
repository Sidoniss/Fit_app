package com.example.fit_app_bachelor.auth.Service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceSingleton {
    private static volatile ApiService INSTANCE = null;

    public static ApiService getInstance() {
        if(INSTANCE == null) {
            synchronized (ApiServiceSingleton.class) {
                if (INSTANCE == null) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://10.0.2.2:8090/api/auth/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    INSTANCE = retrofit.create(ApiService.class);
                }
            }
        }
        return INSTANCE;
    }
}
