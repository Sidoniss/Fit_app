package com.example.fit_app_bachelor.auth.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Patterns;

import com.example.fit_app_bachelor.auth.Service.LoginRepository;
import com.example.fit_app_bachelor.auth.Service.Result;
import com.example.fit_app_bachelor.auth.model.User;
import com.example.fit_app_bachelor.R;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        loginRepository.login(username,password).observeForever(result -> {
            if (result instanceof Result.Success) {
                User data = ((Result.Success<User>) result).getData();
                loginResult.setValue(new LoginResult(new LoggedInUserView(data.getName())));
            } else {
                loginResult.setValue(new LoginResult(R.string.login_failed));
            }
        });
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(null, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, null));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }


    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        return Patterns.EMAIL_ADDRESS.matcher(username).matches();
    }


    private boolean isPasswordValid(String password) {
        return password != null && !password.trim().isEmpty();
    }
}