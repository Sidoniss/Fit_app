package com.example.fit_app_bachelor.login.activities.ui.changePassword;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.fit_app_bachelor.login.Service.LoginRepository;
import com.example.fit_app_bachelor.login.Service.Result;

public class ChangePasswordViewModel extends ViewModel {

    private final MutableLiveData<Result<String>> changePasswordResult;
    private Observer<Result<String>> changePasswordObserver;

    private final LoginRepository loginRepository;

    public ChangePasswordViewModel(LoginRepository loginRepository) {
        changePasswordResult = new MutableLiveData<>();
        this.loginRepository = loginRepository;
    }

    public void ChangePassword(String oldPassword,String newPassword) {
        if (changePasswordObserver != null) {
            loginRepository.changePassword(oldPassword, newPassword).removeObserver(changePasswordObserver);
        }

        changePasswordObserver = new Observer<Result<String>>() {
            @Override
            public void onChanged(Result<String> result) {
                changePasswordResult.postValue(result);
                loginRepository.changePassword(oldPassword,newPassword).removeObserver(this);
            }
        };
        loginRepository.changePassword(oldPassword,newPassword).observeForever(changePasswordObserver);
    }

    public LiveData<Result<String>> getChangePasswordResult() {
        return changePasswordResult;
    }
}
