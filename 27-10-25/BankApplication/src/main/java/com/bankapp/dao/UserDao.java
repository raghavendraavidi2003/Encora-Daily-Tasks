package com.bankapp.dao;

import com.bankapp.model.User;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Random;

@Repository
public class UserDao {

    private final String URL = "jdbc:mysql://localhost:3306/banking_app";
    private final String USER = "root";
    private final String PASSWORD = "Nani@2003";
    
    private static final String INSERT_USER_SQL = "INSERT INTO users (user_id, name, email, password, account_balance) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    private static final String UPDATE_BALANCE_SQL = "UPDATE users SET account_balance = ? WHERE user_id = ?";

    public UserDao() {}

    protected Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public boolean registerUser(User user) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
            
            preparedStatement.setString(1, user.getUserId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setDouble(5, user.getAccountBalance());

            int result = preparedStatement.executeUpdate();
            return result > 0;
        }
    }

    public User getUserByEmail(String email) throws SQLException {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL)) {
            
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getString("user_id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setAccountBalance(rs.getDouble("account_balance"));
            }
        }
        return user;
    }

    public boolean updateBalance(String userId, double newBalance) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BALANCE_SQL)) {
            
            preparedStatement.setDouble(1, newBalance);
            preparedStatement.setString(2, userId);

            int result = preparedStatement.executeUpdate();
            return result > 0;
        }
    }

    public String generateUserId() {
        Random rand = new Random();
        long randomNum = (long)(rand.nextDouble() * 1_000_000_000_000L);
        return "U" + String.format("%013d", randomNum);
    }
}
