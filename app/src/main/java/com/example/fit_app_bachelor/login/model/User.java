package com.example.fit_app_bachelor.login.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class User {


    private String email;

    public User(String email) {

        this.email = email;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}