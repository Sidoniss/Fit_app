package com.example.fit_app_bachelor.login.activities.ui.register;

import androidx.annotation.Nullable;

public class RegisterFormState {
    @Nullable
    private Integer usernameError;
    @Nullable
    private Integer passwordError;
    @Nullable
    private Integer nameError;
    @Nullable
    private Integer checkboxError;
    private boolean isDataValid;

    RegisterFormState(@Nullable Integer usernameError, @Nullable Integer passwordError, @Nullable Integer nameError,@Nullable Integer checkboxError) {
        this.usernameError = usernameError;
        this.passwordError = passwordError;
        this.nameError = nameError;
        this.checkboxError = checkboxError;
        this.isDataValid = false;
    }

    RegisterFormState(boolean isDataValid) {
        this.usernameError = null;
        this.passwordError = null;
        this.nameError = null;
        this.checkboxError = null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    Integer getUsernameError() {
        return usernameError;
    }

    @Nullable
    Integer getPasswordError() {
        return passwordError;
    }

    @Nullable
    public Integer getNameError() {
        return nameError;
    }

    @Nullable
    public Integer getCheckboxError() {
        return checkboxError;
    }

    boolean isDataValid() {
        return isDataValid;
    }
}
