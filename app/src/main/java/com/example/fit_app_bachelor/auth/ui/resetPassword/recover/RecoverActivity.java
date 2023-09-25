package com.example.fit_app_bachelor.auth.ui.resetPassword.recover;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fit_app_bachelor.auth.Service.ApiService;
import com.example.fit_app_bachelor.auth.Service.ApiServiceSingleton;
import com.example.fit_app_bachelor.auth.Service.LoginDataSource;
import com.example.fit_app_bachelor.auth.Service.LoginRepository;
import com.example.fit_app_bachelor.auth.Service.Result;
import com.example.fit_app_bachelor.auth.Service.UserManager;
import com.example.fit_app_bachelor.auth.ui.resetPassword.reset.TokenActivity;
import com.example.fit_app_bachelor.databinding.ActivityRecoverBinding;

public class RecoverActivity extends AppCompatActivity {
    private ActivityRecoverBinding binding;
    private RecoverViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        binding = ActivityRecoverBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ApiService apiService = ApiServiceSingleton.getInstance();
        UserManager userManager = new UserManager(this);
        LoginDataSource loginDataSource = new LoginDataSource(apiService,userManager);
        LoginRepository loginRepository = LoginRepository.getInstance(loginDataSource,userManager);

        RecoverViewModelFactory factory = new RecoverViewModelFactory(loginRepository);

        viewModel = new ViewModelProvider(this,factory).get(RecoverViewModel.class);

        EditText emailEditText = binding.emailToChangePasswordEditText;
        Button nextButton = binding.recoverNextButton;

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                viewModel.sendEmail(email);
            }
        });

        viewModel.getEmailResult().observe(this, new Observer<Result<String>>() {
            @Override
            public void onChanged(Result<String> result) {
                if (result instanceof Result.Success ) {
                    Toast.makeText(RecoverActivity.this, "Email sent successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RecoverActivity.this, TokenActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(RecoverActivity.this, "Invalid email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}