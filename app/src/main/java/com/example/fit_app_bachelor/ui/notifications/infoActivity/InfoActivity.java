package com.example.fit_app_bachelor.ui.notifications.infoActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.fit_app_bachelor.R;
import com.example.fit_app_bachelor.login.Service.UserDatabase;
import com.example.fit_app_bachelor.login.model.User;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        TextView emailTextView = findViewById(R.id.infoEmail);
        TextView nameTextView = findViewById(R.id.infoName);
        TextView dateTextView = findViewById(R.id.infoDate);
        TextView backTextView = findViewById(R.id.infoBack);

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