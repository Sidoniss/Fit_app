package com.example.fit_app_bachelor.login.activities.ui.register;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fit_app_bachelor.MainActivity;
import com.example.fit_app_bachelor.R;
import com.example.fit_app_bachelor.databinding.ActivityRegisterBinding;
import com.example.fit_app_bachelor.login.Service.ApiService;
import com.example.fit_app_bachelor.login.Service.ApiServiceSingleton;
import com.example.fit_app_bachelor.login.Service.UserManager;
import com.example.fit_app_bachelor.login.activities.ui.login.LoginActivity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {
    private  RegisterViewModel registerViewModel;
    private ActivityRegisterBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ApiService apiService = ApiServiceSingleton.getInstance();
        UserManager userManager = new UserManager(this);

        RegisterViewModelFactory factory = new RegisterViewModelFactory(apiService,userManager);

        registerViewModel = new ViewModelProvider(this,factory).get(RegisterViewModel.class);

        final EditText usernameEditText = binding.EmailEditText;
        final EditText passwordEditText = binding.PasswordEditText;
        final EditText nameEditText = binding.nameEditText;
        final Button registerButton = binding.registerButton;
        final CheckBox agreementCheckBox = binding.privacyPolicyCheckbox;
        agreementCheckBox.setMovementMethod(LinkMovementMethod.getInstance());
        final ProgressBar loadingProgressBar = binding.progressBar;
        final TextView backTextView = binding.backTextView;

        registerViewModel.getRegisterFormState().observe(this, new Observer<RegisterFormState>() {
            @Override
            public void onChanged(@Nullable RegisterFormState registerFormState) {
                if (registerFormState == null) {
                    return;
                }
                registerButton.setEnabled(registerFormState.isDataValid());
                registerButton.setEnabled(registerFormState.isDataValid());
                if (registerFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(registerFormState.getUsernameError()));
                }
                if (registerFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(registerFormState.getPasswordError()));
                }
            }
        });

        agreementCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                registerViewModel.registerDataChanged(
                        usernameEditText.getText().toString(),
                        passwordEditText.getText().toString(),
                        nameEditText.getText().toString(),
                        isChecked  // Dodajemy stan checkboxa jako nowy argument.
                );
            }
        });

        registerViewModel.getRegisterResult().observe(this, new Observer<RegisterResult>() {
            @Override
            public void onChanged(@Nullable RegisterResult registerResult) {
                Log.d("RegisterActiv", "Observer triggered: " + registerResult);

                if (registerResult == null) {
                    Log.d("RegisterActiv", "Result is null");
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                Log.d("RegisterActiv", "Checking for error...");
                if (registerResult.getError() != null) {
                    Log.d("RegisterActiv", "Error found: " + registerResult.getError());
                    showRegisterFailed(registerResult.getError());
                }
                Log.d("RegisterActiv", "Checking for success...");
                if (registerResult.getSuccess() != null) {
                    Log.d("RegisterActiv", "success: " + registerResult.getSuccess().getDisplayName());
                    RegisterInUserView registeredUser = new RegisterInUserView(registerResult.getSuccess().getDisplayName());
                    updateUiWithUser(registeredUser);
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Log.d("RegisterActiv", "No success found.");
                }
                setResult(Activity.RESULT_OK);
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                registerViewModel.registerDataChanged(
                        usernameEditText.getText().toString(),
                        passwordEditText.getText().toString(),
                        nameEditText.getText().toString(),
                        agreementCheckBox.isChecked()
                );
            }
        };

        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        nameEditText.addTextChangedListener(afterTextChangedListener);

        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    registerViewModel.register(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString(),nameEditText.getText().toString());
                }
                return false;
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                registerViewModel.register(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString(),nameEditText.getText().toString());
            }
        });

        backTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }



    private void updateUiWithUser(RegisterInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);

        // Zakończ LoginActivity
        finish();
    }

    private void showRegisterFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}
