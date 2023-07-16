package com.example.fit_app_bachelor.ui.dashboard;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class DashboardViewModelFactory implements ViewModelProvider.Factory {
    private Application mapplication;

    public DashboardViewModelFactory(Application application) {
        mapplication = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new DashboardViewModel(mapplication);
    }
}
