package com.example.fit_app_bachelor.auth.model;

public class RecoverRequest {
    private String email;

    public RecoverRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
