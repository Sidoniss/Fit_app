package com.example.fit_app_bachelor.ui.notifications;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.example.fit_app_bachelor.R;

public class NotificationsFragment extends PreferenceFragmentCompat {


    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.preferences,rootKey);

        Preference infoButton = findPreference("infoButton");
        Preference changePasswordButton = findPreference("changePasswordButton");

        if (infoButton != null) {
            infoButton.setOnPreferenceClickListener(preference -> {

                return true;
            });
        }

        if (changePasswordButton != null) {
            changePasswordButton.setOnPreferenceClickListener(preference -> {

                return true;
            });
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}