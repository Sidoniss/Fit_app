package com.example.fit_app_bachelor.login.activities.ui.register;

public class RegisterInUserView {
    private String displayName;
    //... other data fields that may be accessible to the UI

    RegisterInUserView(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}