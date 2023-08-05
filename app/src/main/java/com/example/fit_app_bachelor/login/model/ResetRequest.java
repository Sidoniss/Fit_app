package com.example.fit_app_bachelor.login.model;

public class ResetRequest {
    private String newPassword;
    private String token;

    public ResetRequest( String newPassword, String token) {
        this.newPassword = newPassword;
        this.token = token;
    }
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
