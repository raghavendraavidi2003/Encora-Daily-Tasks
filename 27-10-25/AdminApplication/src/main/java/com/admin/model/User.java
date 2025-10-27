package com.admin.model;

public class User {
    private String id;
    private String name;
    private String email;
    private String pwd;
    private double balance;

    public User() {}

    public User(String id, String name, String email, String pwd, double balance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pwd = pwd;
        this.balance = balance;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPwd() { return pwd; }
    public void setPwd(String pwd) { this.pwd = pwd; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}
