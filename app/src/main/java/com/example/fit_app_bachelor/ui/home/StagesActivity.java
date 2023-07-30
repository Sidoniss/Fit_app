package com.example.fit_app_bachelor.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.fit_app_bachelor.R;
import com.example.fit_app_bachelor.databinding.ActivityStagesBinding;
import com.example.fit_app_bachelor.ui.home.model.Stage;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StagesActivity extends AppCompatActivity {

    private ActivityStagesBinding binding;
    private List<Stage> stageList;
    private int currentStageIndex = 0;
    private boolean isBreakStage = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityStagesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        stageList = (List<Stage>) getIntent().getSerializableExtra("stages");
        setupStage();

        binding.stagesNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentStageIndex < stageList.size() -1) {
                    isBreakStage=true;
                }
                nextStage();
            }
        });
    }

    private void setupStage() {
        if (currentStageIndex >= stageList.size()) {
            finish();
            return;
        }
        if (isBreakStage) {
            setupBreakStage();
        } else {
            Stage stage = stageList.get(currentStageIndex);

            Stage currentStage = stageList.get(currentStageIndex);
            binding.stagesTitleTextView.setText(currentStage.getDescription());
            Picasso.get().load(stage.getImageUrl()).into(binding.stagesImageView);
            switch (currentStage.getType()) {
                case 0:
                    setupType0(currentStage.getTime());
                    break;
                case 1:
                    setupType1();
                    break;
        }
        }
    }

    private void setupType0(int time) {
        final int totalTimeInMillis = time * 1000;
        binding.stagesNextButton.setVisibility(View.GONE);

        new CountDownTimer(totalTimeInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int progress = (int) ((totalTimeInMillis - millisUntilFinished) * 100 / totalTimeInMillis);
                binding.stagesProgressBar.setProgress(progress);
            }

            @Override
            public void onFinish() {
                if(currentStageIndex < stageList.size() -1) {
                    isBreakStage=true;
                }
                nextStage();
            }
        }.start();
    }

    private void setupBreakStage() {
        binding.stagesNextButton.setVisibility(View.GONE);

        new CountDownTimer( 10000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                binding.stagesProgressBar.setProgress((int) (10000 - millisUntilFinished) / 100);
            }

            @Override
            public void onFinish() {
                isBreakStage = false;
                nextStage();
            }
        }.start();
    }

    private void setupType1() {
        binding.stagesNextButton.setVisibility(View.VISIBLE);
    }

    private void nextStage() {
        if(isBreakStage) {
            setupStage();
        } else {
            currentStageIndex++;
            setupStage();

        }
    }
}