package com.example.fit_app_bachelor.login.activities.ui.resetPassword.reset;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.fit_app_bachelor.login.Service.LoginRepository;
import com.example.fit_app_bachelor.login.Service.Result;

public class TokenViewModel extends ViewModel {
    private final MutableLiveData<Result<String>> tokenResult;
    private final LoginRepository loginRepository;

    public TokenViewModel( LoginRepository loginRepository) {
        this.tokenResult = new MutableLiveData<>();
        this.loginRepository = loginRepository;
    }

    public void resetPassword(String newPassword, String token) {
        loginRepository.resetPassword(newPassword, token).observeForever(new Observer<Result<String>>() {
            @Override
            public void onChanged(Result<String> result) {
                tokenResult.postValue(result);
                loginRepository.resetPassword(newPassword, token).removeObserver(this);
            }
        });
    }

    public LiveData<Result<String>> getTokenResult() {
        return tokenResult;
    }
}
