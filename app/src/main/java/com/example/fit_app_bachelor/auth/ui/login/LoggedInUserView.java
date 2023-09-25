package com.example.fit_app_bachelor.auth.ui.login;


class LoggedInUserView {
    private String displayName;

    LoggedInUserView(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}