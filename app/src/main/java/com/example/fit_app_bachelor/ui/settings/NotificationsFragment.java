package com.example.fit_app_bachelor.ui.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.example.fit_app_bachelor.R;
import com.example.fit_app_bachelor.auth.Service.ApiService;
import com.example.fit_app_bachelor.auth.Service.ApiServiceSingleton;
import com.example.fit_app_bachelor.auth.Service.LoginDataSource;
import com.example.fit_app_bachelor.auth.Service.LoginRepository;
import com.example.fit_app_bachelor.auth.Service.UserManager;
import com.example.fit_app_bachelor.auth.ui.changePassword.ChangePasswordActivity;
import com.example.fit_app_bachelor.auth.ui.login.LoginActivity;
import com.example.fit_app_bachelor.ui.settings.infoActivity.InfoActivity;

public class NotificationsFragment extends PreferenceFragmentCompat {


    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.preferences,rootKey);

        Preference infoButton = findPreference("infoButton");
        Preference changePasswordButton = findPreference("changePasswordButton");
        Preference logOutButton = findPreference("logoutButton");

        if (infoButton != null) {
            infoButton.setOnPreferenceClickListener(preference -> {
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                startActivity(intent);
                return true;
            });
        }

        if (changePasswordButton != null) {
            changePasswordButton.setOnPreferenceClickListener(preference -> {
                Intent intent = new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(intent);
                return true;
            });
        }


        if (logOutButton != null) {
            logOutButton.setOnPreferenceClickListener(preference -> {
                Context context = getContext();
                if (context != null) {
                    ApiService apiService = ApiServiceSingleton.getInstance();
                    UserManager userManager = new UserManager(context);
                    LoginDataSource loginDataSource = new LoginDataSource(apiService,userManager);
                    LoginRepository.getInstance(loginDataSource,userManager).logout();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    if (getActivity() != null) {
                        getActivity().finish();
                    }
                }
                return true;
            });
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}