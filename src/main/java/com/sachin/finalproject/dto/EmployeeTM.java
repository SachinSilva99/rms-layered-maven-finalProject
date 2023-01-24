package com.sachin.finalproject.dto;

import com.jfoenix.controls.JFXButton;

public class EmployeeTM {
    private String nic;
    private String name;
    private String role;
    private JFXButton button;

    @Override
    public String toString() {
        return "EmployeeTM{" +
                "nic='" + nic + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", button=" + button +
                '}';
    }

    public EmployeeTM() {
    }

    public EmployeeTM(String nic, String name, String role, JFXButton button) {
        this.setNic(nic);
        this.setName(name);
        this.setRole(role);
        this.setButton(button);
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

    public JFXButton getButton() {
        return button;
    }

    public void setButton(JFXButton button) {
        this.button = button;
    }
}
