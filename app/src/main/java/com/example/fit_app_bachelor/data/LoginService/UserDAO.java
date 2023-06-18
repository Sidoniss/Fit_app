package com.example.fit_app_bachelor.data.LoginService;

import com.example.fit_app_bachelor.data.model.LoggedInUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public static LoggedInUser authenticate(String email,String password) {
        String query = "SELECT * FROM users WHERE email = ?";
        try (Connection connection = DataBaseConnection.GetConection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                String userId = resultSet.getString("id");
                String passwordHash = resultSet.getString("password");
                LoggedInUser loggedInUser = new LoggedInUser(userId,passwordHash,email);
                if (loggedInUser.checkPassword(password)) {
                    return loggedInUser;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
