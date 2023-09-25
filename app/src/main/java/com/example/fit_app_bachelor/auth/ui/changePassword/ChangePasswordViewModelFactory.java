package com.example.fit_app_bachelor.auth.ui.changePassword;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.fit_app_bachelor.auth.Service.LoginRepository;

public class ChangePasswordViewModelFactory implements ViewModelProvider.Factory {

    private final LoginRepository loginRepository;

    public ChangePasswordViewModelFactory(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ChangePasswordViewModel.class)) {
            return (T) new ChangePasswordViewModel(loginRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
