package com.example.fit_app_bachelor.auth.model;

public class LoginResponse {

    private String email;
    private String name;
    private String accountCreationDate;

    public LoginResponse( String email) {

        this.email = email;
    }

    public LoginResponse(String email, String name, String accountCreationDate) {
        this.email = email;
        this.name = name;
        this.accountCreationDate = accountCreationDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountCreationDate() {
        return accountCreationDate;
    }

    public void setAccountCreationDate(String accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }
}
