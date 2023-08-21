package com.example.fit_app_bachelor.login.activities.ui.resetPassword.reset;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fit_app_bachelor.R;
import com.example.fit_app_bachelor.databinding.ActivityTokenBinding;
import com.example.fit_app_bachelor.login.Service.ApiService;
import com.example.fit_app_bachelor.login.Service.ApiServiceSingleton;
import com.example.fit_app_bachelor.login.Service.LoginDataSource;
import com.example.fit_app_bachelor.login.Service.LoginRepository;
import com.example.fit_app_bachelor.login.Service.Result;
import com.example.fit_app_bachelor.login.Service.UserManager;
import com.example.fit_app_bachelor.login.activities.ui.login.LoginActivity;
import com.example.fit_app_bachelor.login.activities.ui.resetPassword.recover.RecoverActivity;

public class TokenActivity extends AppCompatActivity {
    private ActivityTokenBinding binding;

    private TokenViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTokenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ApiService apiService = ApiServiceSingleton.getInstance();
        UserManager userManager = new UserManager(this);
        LoginDataSource loginDataSource = new LoginDataSource(apiService,userManager);
        LoginRepository loginRepository = LoginRepository.getInstance(loginDataSource,userManager);

        TokenViewModelFactory factory = new TokenViewModelFactory(loginRepository);

        viewModel = new ViewModelProvider(this,factory).get(TokenViewModel.class);

        TextView backTextView = binding.tokenBackButton;
        Button resetPasswordButton = binding.ResetPasswordButton;
        EditText newPasswordEditText = binding.newPasswordEditText;
        EditText tokenEditText = binding.tokenEditText;

        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newPassword = newPasswordEditText.getText().toString();
                String token = tokenEditText.getText().toString();
                viewModel.resetPassword(newPassword,token);
            }
        });

        viewModel.getTokenResult().observe(this, new Observer<Result<String>>() {
            @Override
            public void onChanged(Result<String> result) {
                if (result instanceof Result.Success) {
                    Toast.makeText(TokenActivity.this, "Password reset successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TokenActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(TokenActivity.this, "Invalid token or password", Toast.LENGTH_SHORT).show();
                }
            }
        });


        backTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TokenActivity.this, RecoverActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}