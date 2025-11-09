package com.example.project.Model;

public class User {

    private int user_Id;
    private String name;

    public User (int user_Id, String name) {
        this.user_Id = user_Id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return user_Id;
    }

    public void setUserId(int userId) {
        this.user_Id = userId;
    }

}
