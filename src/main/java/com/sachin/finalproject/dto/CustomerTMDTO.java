package com.sachin.finalproject.dto;

import com.jfoenix.controls.JFXButton;

public class CustomerTMDTO {
    private String name;
    private String gender;
    private String address;
    private String id;
    private String phoneNumber;
    private JFXButton button;

    public CustomerTMDTO() {
    }

    public CustomerTMDTO(String name, String gender, String address, String id, String phoneNumber, JFXButton button) {
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.button = button;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public JFXButton getButton() {
        return button;
    }

    public void setButton(JFXButton button) {
        this.button = button;
    }
}
