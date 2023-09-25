package com.example.fit_app_bachelor.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        binding = ActivityStagesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        stageList = (List<Stage>) getIntent().getSerializableExtra("stages");
        setupStage();
        Log.d("StagesActivity", "Activity created with " + stageList.size() + " stages.");

        binding.stagesNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentStageIndex < stageList.size() && stageList.get(currentStageIndex).getType() == 0) {
                    setupType0(stageList.get(currentStageIndex).getTime());
                } else {
                    nextStage();
                }
            }
        });
    }

    private void setupStage() {

        if (currentStageIndex >= stageList.size()) {
            setupFinalStage();
            return;
        }

        Stage stage = stageList.get(currentStageIndex);
        binding.stagesReadyTextView.setVisibility(View.INVISIBLE);
        Stage currentStage = stageList.get(currentStageIndex);
        binding.stagesTitleTextView.setText(currentStage.getDescription());
        Picasso.get().load(stage.getImageUrl()).into(binding.stagesImageView);
        switch (currentStage.getType()) {
            case 0:
                initiateBreakAndStage0();
                break;
            case 1:
                setupType1();
                break;

        }
    }

    private void initiateBreakAndStage0() {
        setupBreakStage();
    }

    private void setupType0(int time) {
        final int totalTimeInMillis = time * 1000;
        binding.stagesNextButton.setVisibility(View.GONE);
        binding.stagesReadyTextView.setVisibility(View.GONE);
        binding.stagesProgressBar.setVisibility(View.VISIBLE);

        new CountDownTimer(totalTimeInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int progress = (int) ((totalTimeInMillis - millisUntilFinished) * 100 / totalTimeInMillis);
                binding.stagesProgressBar.setProgress(progress);
            }

            @Override
            public void onFinish() {
                nextStage();
            }
        }.start();
    }

    private void setupBreakStage() {
        binding.stagesNextButton.setVisibility(View.VISIBLE);
        binding.stagesNextButton.setText(R.string.ready);
        binding.stagesReadyTextView.setVisibility(View.VISIBLE);
        binding.stagesProgressBar.setVisibility(View.GONE);

        if (currentStageIndex < stageList.size()) {
            Stage currentStage = stageList.get(currentStageIndex);
            binding.stagesTitleTextView.setText(currentStage.getDescription());
            Picasso.get().load(currentStage.getImageUrl()).into(binding.stagesImageView);
        } else {
            binding.stagesTitleTextView.setText("");
            binding.stagesImageView.setImageDrawable(null);
        }
    }

    private void setupType1() {
        binding.stagesNextButton.setVisibility(View.VISIBLE);
        binding.stagesNextButton.setText(R.string.next);
        binding.stagesProgressBar.setVisibility(View.GONE);
    }

    private void setupFinalStage() {
        binding.stagesNextButton.setVisibility(View.VISIBLE);
        binding.stagesNextButton.setText(R.string.finish);
        binding.stagesProgressBar.setVisibility(View.GONE);
        binding.stagesImageView.setImageDrawable(null);
        binding.stagesReadyTextView.setVisibility(View.GONE);
        binding.stagesTitleTextView.setText(getString(R.string.congratulations));

        binding.stagesNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void nextStage() {
        currentStageIndex++;
        setupStage();
    }
}