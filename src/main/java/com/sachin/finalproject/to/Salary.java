package com.sachin.finalproject.to;

import java.time.LocalDate;
import java.util.Date;

public class Salary {
    private String enic;
    private String name;
    private double salary;
    private LocalDate month;
    private LocalDate date;
    private  String status;

    public Salary() {
    }

    public String getEnic() {
        return enic;
    }

    public void setEnic(String enic) {
        this.enic = enic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getMonth() {
        return month;
    }

    public void setMonth(LocalDate month) {
        this.month = month;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Salary(String enic, String name, double salary, LocalDate month, LocalDate date, String status) {
        this.enic = enic;
        this.name = name;
        this.salary = salary;
        this.month = month;
        this.date = date;
        this.status = status;
    }
}
