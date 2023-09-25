package com.example.fit_app_bachelor.auth.ui.resetPassword.reset;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.fit_app_bachelor.auth.Service.LoginRepository;

public class TokenViewModelFactory implements ViewModelProvider.Factory {
    private final LoginRepository loginRepository;

    public TokenViewModelFactory(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TokenViewModel.class)) {
            return (T) new TokenViewModel(loginRepository);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
