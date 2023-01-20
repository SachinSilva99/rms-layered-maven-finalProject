package com.sachin.finalproject.to;

import java.time.LocalDate;

public class Employee {

    private String phoneNumber;
    private String nic;
    private String name;
    private String role;
    private double salary;
    private LocalDate dob;
    private String address;
    private String gender;

    public Employee(String phoneNumber, String nic, String name, String role, double salary, LocalDate dob, String address, String gender) {
        this.phoneNumber = phoneNumber;
        this.nic = nic;
        this.name = name;
        this.role = role;
        this.salary = salary;
        this.dob = dob;
        this.address = address;
        this.gender = gender;
    }

    public Employee() {
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
