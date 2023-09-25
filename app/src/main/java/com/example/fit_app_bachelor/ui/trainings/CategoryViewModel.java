package com.example.fit_app_bachelor.ui.trainings;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.fit_app_bachelor.ui.trainings.model.Training;
import com.example.fit_app_bachelor.ui.trainings.service.TrainingDAO;

import java.util.List;

public class CategoryViewModel extends ViewModel {

    private final MutableLiveData<Integer> selectedCategory = new MutableLiveData<>();
    private final LiveData<List<Training>> trainings;

    public CategoryViewModel(TrainingDAO trainingDAO) {
        this.trainings = Transformations.switchMap(selectedCategory,
                trainingDAO::getTrainingsByCategory);
    }

    public void selectCategory(int category) {
        selectedCategory.setValue(category);
    }

    public LiveData<List<Training>> getTrainings() {
        return trainings;
    }

    public static class Factory implements ViewModelProvider.Factory {
        private final TrainingDAO trainingDAO;

        public Factory(TrainingDAO trainingDAO) {
            this.trainingDAO = trainingDAO;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(CategoryViewModel.class)) {
                return (T) new CategoryViewModel(trainingDAO);
            }
            throw new IllegalArgumentException("Unknown ViewModel class.");
        }
    }
}
