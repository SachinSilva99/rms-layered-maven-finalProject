package com.sachin.finalproject.entity;

import javafx.scene.image.Image;

public class User implements SuperEntity{
    private String username;
    private String password;
    private String role;
    private String userType;
    private Image img;

    private String name;
    public User() {
    }

    public User(String username, String password, String role, String userType, Image img, String name) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.userType = userType;
        this.img = img;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", userType='" + userType + '\'' +
                ", img=" + img +
                ", name='" + name + '\'' +
                '}';
    }
}
