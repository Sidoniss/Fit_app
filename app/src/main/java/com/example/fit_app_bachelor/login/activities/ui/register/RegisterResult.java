package com.example.fit_app_bachelor.login.activities.ui.register;

import androidx.annotation.Nullable;


public class RegisterResult {
    @Nullable
    private RegisterInUserView success;
    @Nullable
    private Integer error;

    RegisterResult(@Nullable Integer error) {
        this.error = error;
    }

    RegisterResult(@Nullable RegisterInUserView success) {
        this.success = success;
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
