package com.example.fit_app_bachelor.ui.login;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.example.fit_app_bachelor.data.LoginService.LoginDataSource;
import com.example.fit_app_bachelor.data.LoginService.LoginRepository;
import com.example.fit_app_bachelor.data.LoginService.UserDAO;

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
public class LoginViewModelFactory implements ViewModelProvider.Factory {
    private final UserDAO userDAO;

    public LoginViewModelFactory(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(LoginRepository.getInstance(new LoginDataSource(userDAO)));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}