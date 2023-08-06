package com.example.fit_app_bachelor.login.activities.ui.changePassword;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.fit_app_bachelor.login.Service.LoginRepository;
import com.example.fit_app_bachelor.login.Service.Result;

public class ChangePasswordViewModel extends ViewModel {

    private final MutableLiveData<Result<String>> changePasswordResult;

    private final LoginRepository loginRepository;

    public ChangePasswordViewModel(LoginRepository loginRepository) {
        changePasswordResult = new MutableLiveData<>();
        this.loginRepository = loginRepository;
    }

    public void ChangePassword(String email,String oldPassword,String newPassword) {
        loginRepository.changePassword(email,oldPassword,newPassword).observeForever(new Observer<Result<String>>() {
            @Override
            public void onChanged(Result<String> result) {
                changePasswordResult.postValue(result);
                loginRepository.changePassword(email, oldPassword, newPassword).removeObserver(this);
            }
        });
    }

    public LiveData<Result<String>> getChangePasswordResult() {
        return changePasswordResult;
    }
}
