package com.example.fit_app_bachelor.ui.settings.infoActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.fit_app_bachelor.R;
import com.example.fit_app_bachelor.auth.Service.UserDatabase;
import com.example.fit_app_bachelor.auth.model.User;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        TextView emailTextView = findViewById(R.id.infoEmail);
        TextView nameTextView = findViewById(R.id.infoName);
        TextView dateTextView = findViewById(R.id.infoDate);

        new Thread(() -> {
            User user = UserDatabase.getInstance(InfoActivity.this).userDAO().getUser();

            if (user != null) {
                runOnUiThread(() -> {
                    emailTextView.setText(user.getEmail());
                    nameTextView.setText(user.getName());
                    dateTextView.setText(user.getAccountCreationDate());
                });
            }
        }).start();


    }
}