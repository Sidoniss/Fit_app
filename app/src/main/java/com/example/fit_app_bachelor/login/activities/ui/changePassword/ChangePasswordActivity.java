package com.example.fit_app_bachelor.login.activities.ui.changePassword;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fit_app_bachelor.R;
import com.example.fit_app_bachelor.databinding.ActivityChangePasswordBinding;
import com.example.fit_app_bachelor.login.Service.ApiService;
import com.example.fit_app_bachelor.login.Service.ApiServiceSingleton;
import com.example.fit_app_bachelor.login.Service.LoginDataSource;
import com.example.fit_app_bachelor.login.Service.LoginRepository;
import com.example.fit_app_bachelor.login.Service.Result;
import com.example.fit_app_bachelor.login.Service.UserManager;

public class ChangePasswordActivity extends AppCompatActivity {
    private ActivityChangePasswordBinding binding;

    private ChangePasswordViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        binding = ActivityChangePasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ApiService apiService = ApiServiceSingleton.getInstance();
        UserManager userManager = new UserManager(this);
        LoginDataSource loginDataSource = new LoginDataSource(apiService,userManager);
        LoginRepository loginRepository = LoginRepository.getInstance(loginDataSource,userManager);

        ChangePasswordViewModelFactory factory = new ChangePasswordViewModelFactory(loginRepository);

        viewModel = new ViewModelProvider(this,factory).get(ChangePasswordViewModel.class);

        EditText oldPasswordEditText = binding.ChangePasswordOldPassword;
        EditText newPasswordEditText = binding.ChangePasswordNewPassword;
        EditText confirmNewPasswordEditText = binding.ChangePasswordConfirmNewPassword;
        Button changePasswordButton = binding.ChangePasswordButton;

        viewModel.getChangePasswordResult().observe(ChangePasswordActivity.this,result -> {
            if(result instanceof Result.Success) {
                Toast.makeText(ChangePasswordActivity.this,"Zmiana hasła udana!",Toast.LENGTH_SHORT).show();
                finish();
            } else if (result instanceof Result.Error) {
                Toast.makeText(ChangePasswordActivity.this,"Błąd zmiany hasła!",Toast.LENGTH_SHORT).show();
            }
        });

        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPassword = oldPasswordEditText.getText().toString().trim();
                String newPassword = newPasswordEditText.getText().toString().trim();
                String confirmNewPassword = confirmNewPasswordEditText.getText().toString().trim();

                if (!PasswordValidator.isValid(newPassword) || !newPassword.equals(confirmNewPassword)) {
                    return;
                }

                viewModel.ChangePassword(oldPassword,newPassword);
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!PasswordValidator.isValid(newPasswordEditText.getText().toString())) {
                    newPasswordEditText.setError("Hasło musi mieć co najmniej 5 znaków!");
                } else {
                    newPasswordEditText.setError(null);
                }

                if (!newPasswordEditText.getText().toString().equals(confirmNewPasswordEditText.getText().toString())) {
                    confirmNewPasswordEditText.setError("Hasła nie są zgodne!");
                } else {
                    confirmNewPasswordEditText.setError(null);
                }
            }
        };

        newPasswordEditText.addTextChangedListener(afterTextChangedListener);
        confirmNewPasswordEditText.addTextChangedListener(afterTextChangedListener);
    }
}