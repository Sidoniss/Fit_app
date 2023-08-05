package com.example.fit_app_bachelor.login.activities.ui.resetPassword.recover;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.fit_app_bachelor.login.Service.ApiService;
import com.example.fit_app_bachelor.login.Service.LoginDataSource;
import com.example.fit_app_bachelor.login.Service.LoginRepository;

public class RecoverViewModelFactory implements ViewModelProvider.Factory {
    private final LoginRepository loginRepository;

    public RecoverViewModelFactory(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RecoverViewModel.class)) {
            return (T) new RecoverViewModel(loginRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
