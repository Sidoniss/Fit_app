package com.example.fit_app_bachelor.auth.ui.register;

import androidx.annotation.Nullable;


public class RegisterResult {
    @Nullable
    private RegisterInUserView success;
    @Nullable
    private Integer error;

    @Nullable
    private String message;

    public RegisterResult(String message) {
        this.message=message;
    }

    RegisterResult(@Nullable Integer error) {
        this.error = error;
    }

    RegisterResult(@Nullable RegisterInUserView success) {
        this.success = success;
    }

    @Nullable
    public String getMessage() {
        return message;
    }

    @Nullable
    RegisterInUserView getSuccess() {
        return success;
    }

    @Nullable
    Integer getError() {
        return error;
    }
}
