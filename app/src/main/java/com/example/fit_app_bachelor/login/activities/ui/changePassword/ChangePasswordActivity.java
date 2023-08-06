package com.example.fit_app_bachelor.login.activities.ui.changePassword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fit_app_bachelor.R;
import com.example.fit_app_bachelor.databinding.ActivityChangePasswordBinding;
import com.example.fit_app_bachelor.login.Service.ApiService;
import com.example.fit_app_bachelor.login.Service.ApiServiceSingleton;
import com.example.fit_app_bachelor.login.Service.LoginDataSource;
import com.example.fit_app_bachelor.login.Service.LoginRepository;

public class ChangePasswordActivity extends AppCompatActivity {
    private ActivityChangePasswordBinding binding;

    private ChangePasswordViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ApiService apiService = ApiServiceSingleton.getInstance();
        LoginDataSource loginDataSource = new LoginDataSource(apiService);
        LoginRepository loginRepository = LoginRepository.getInstance(loginDataSource);

        ChangePasswordViewModelFactory factory = new ChangePasswordViewModelFactory(loginRepository);

        viewModel = new ViewModelProvider(this,factory).get(ChangePasswordViewModel.class);

        TextView backTextView = binding.ChangePasswordBackTextView;
        EditText oldPasswordEditText = binding.ChangePasswordOldPassword;
        EditText newPasswordEditText = binding.ChangePasswordNewPassword;
        EditText confirmNewPasswordEditText = binding.ChangePasswordConfirmNewPassword;
        Button changePasswordButton = binding.ChangePasswordButton;

        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}