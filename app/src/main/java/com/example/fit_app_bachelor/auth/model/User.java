package com.example.fit_app_bachelor.auth.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    private String email;
    private String name;
    private String accountCreationDate;

    public User(String email, String name, String accountCreationDate) {
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

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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