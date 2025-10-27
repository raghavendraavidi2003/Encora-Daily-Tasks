package com.bankapp.model;

public class User {
    private String userId;
    private String name;
    private String email;
    private String password;
    private double accountBalance;

    // Default constructor
    public User() {}

    // Parameterized constructor
    public User(String userId, String name, String email, String password, double accountBalance) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.accountBalance = accountBalance;
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", accountBalance=" + accountBalance +
                '}';
    }
}
