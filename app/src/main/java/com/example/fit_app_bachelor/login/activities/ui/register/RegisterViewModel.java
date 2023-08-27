package com.example.fit_app_bachelor.login.activities.ui.register;

import android.util.Log;
import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fit_app_bachelor.R;
import com.example.fit_app_bachelor.login.Service.LoginRepository;
import com.example.fit_app_bachelor.login.Service.Result;
import com.example.fit_app_bachelor.login.model.User;

public class RegisterViewModel extends ViewModel {

    private MutableLiveData<RegisterFormState> registerFormState = new MutableLiveData<>();
    private MutableLiveData<RegisterResult> registerResult = new MutableLiveData<>();
    private LoginRepository loginRepository;
    public RegisterViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<RegisterFormState> getRegisterFormState() {return registerFormState;}

    LiveData<RegisterResult> getRegisterResult() {return registerResult;}

    public void register(String username,String password,String name) {
        loginRepository.register(username,password,name).observeForever(result -> {
            Log.d("RegisterViewModel", "succes: " + result);

            if (result instanceof Result.Success) {
                String data = ((Result.Success<String>) result).getData();
                registerResult.setValue(new RegisterResult(new RegisterInUserView(data)));
            } else {
                registerResult.setValue(new RegisterResult(R.string.register_failed));
            }
        });
    }

    public void registerDataChanged(String username, String password,String name,boolean isCheckboxChecked) {
        if (!isUserNameValid(username)) {
            registerFormState.setValue(new RegisterFormState(R.string.invalid_username, null, null,null));
        } else if (!isPasswordValid(password)) {
            registerFormState.setValue(new RegisterFormState(null, R.string.invalid_password, null,null));
        } else if (!isNameValid(name)) {
            registerFormState.setValue(new RegisterFormState(null, null, R.string.invalid_name,null));
        } else if (!isCheckboxChecked) {

            registerFormState.setValue(new RegisterFormState(null, null, null, R.string.checkbox_error));
        } else {
            registerFormState.setValue(new RegisterFormState(true));
        }
    }

    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        return Patterns.EMAIL_ADDRESS.matcher(username).matches();
    }

    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

    private boolean isNameValid(String name) {
        return name != null && name.trim().length() > 2;
    }
}
