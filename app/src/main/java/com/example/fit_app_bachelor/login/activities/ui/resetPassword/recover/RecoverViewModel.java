package com.example.fit_app_bachelor.login.activities.ui.resetPassword.recover;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.fit_app_bachelor.login.Service.LoginRepository;
import com.example.fit_app_bachelor.login.Service.Result;

public class RecoverViewModel extends ViewModel {
    private final MutableLiveData<Result<String>> emailResult;
    private final LoginRepository loginRepository;

    public RecoverViewModel(LoginRepository loginRepository) {
        this.emailResult = new MutableLiveData<>();
        this.loginRepository = loginRepository;
    }

    public void sendEmail(String email) {
        loginRepository.sendEmailWithToken(email).observeForever(new Observer<Result<String>>() {
            @Override
            public void onChanged(Result<String> result) {
                emailResult.postValue(result);
                loginRepository.sendEmailWithToken(email).removeObserver(this);
            }
        });
    }

    public LiveData<Result<String>> getEmailResult() {
        return emailResult;
    }
}
