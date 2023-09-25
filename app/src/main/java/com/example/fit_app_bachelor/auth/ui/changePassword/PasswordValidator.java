package com.example.fit_app_bachelor.auth.ui.changePassword;

public class PasswordValidator {
    private static final int MIN_PASSWORD_LENGTH = 5;

    public static boolean isValid(String password) {
        if (password == null) {
            return false;
        }

        return password.length() >= MIN_PASSWORD_LENGTH;
    }
}
