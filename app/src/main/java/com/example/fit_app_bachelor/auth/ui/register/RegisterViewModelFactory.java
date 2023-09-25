package com.example.fit_app_bachelor.auth.ui.register;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.fit_app_bachelor.auth.Service.ApiService;
import com.example.fit_app_bachelor.auth.Service.LoginDataSource;
import com.example.fit_app_bachelor.auth.Service.LoginRepository;
import com.example.fit_app_bachelor.auth.Service.UserManager;

public class RegisterViewModelFactory implements ViewModelProvider.Factory {
    private ApiService apiService;
    private UserManager userManager;

    public RegisterViewModelFactory(ApiService apiService,UserManager userManager) {
        this.apiService = apiService;
        this.userManager = userManager;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RegisterViewModel.class)) {
            return (T) new RegisterViewModel(LoginRepository.getInstance(new LoginDataSource(apiService,userManager),userManager));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
