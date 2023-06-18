package com.example.fit_app_bachelor.data.model;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String userId;
    private String passwordHash;
    private String email;

    public LoggedInUser(String userId,String password, String email) {
        this.userId = userId;
        this.passwordHash = BCrypt.hashpw(password,BCrypt.gensalt());
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public Boolean checkPassword(String password) {return BCrypt.checkpw(password,passwordHash);}
}