package com.example.fit_app_bachelor.auth.ui.login;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.example.fit_app_bachelor.auth.Service.ApiService;
import com.example.fit_app_bachelor.auth.Service.LoginDataSource;
import com.example.fit_app_bachelor.auth.Service.LoginRepository;
import com.example.fit_app_bachelor.auth.Service.UserManager;


public class LoginViewModelFactory implements ViewModelProvider.Factory {
    private final ApiService apiService;
    private UserManager userManager;

    public LoginViewModelFactory(ApiService apiService, UserManager userManager) {
        this.apiService = apiService;
        this.userManager = userManager;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(LoginRepository.getInstance(new LoginDataSource(apiService,userManager),userManager));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}