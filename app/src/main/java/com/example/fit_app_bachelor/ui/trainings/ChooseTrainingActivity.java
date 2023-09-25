package com.example.fit_app_bachelor.ui.trainings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.example.fit_app_bachelor.databinding.ActivityChooseTreningBinding;
import com.example.fit_app_bachelor.ui.trainings.service.TrainingDatabase;
import com.example.fit_app_bachelor.ui.trainings.service.TrainingsAdapter;

import java.util.ArrayList;

public class ChooseTrainingActivity extends AppCompatActivity {

    private ActivityChooseTreningBinding binding;
    private CategoryViewModel viewModel;
    private TrainingsAdapter adapter;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        TrainingDatabase db = TrainingDatabase.getInstance(this);
        viewModel = new ViewModelProvider(this,new CategoryViewModel.Factory(db.trainingDAO()))
                .get(CategoryViewModel.class);

        binding = ActivityChooseTreningBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int category = getIntent().getIntExtra("category", -1); // zakładając, że -1 oznacza brak kategorii
        if (category == -1) {
            Toast.makeText(this, "Nieprawidłowa kategoria", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        viewModel.selectCategory(category);

        RecyclerView trainingRecyclerView = binding.trainingRecyclerView;

        trainingRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        adapter = new TrainingsAdapter(new ArrayList<>());
        trainingRecyclerView.setAdapter(adapter);

        viewModel.getTrainings().observe(this,trainings -> {
            adapter.setTrainings(trainings);
            adapter.notifyDataSetChanged();
        });

    }
}