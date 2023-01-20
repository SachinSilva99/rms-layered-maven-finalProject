package com.sachin.finalproject.entity;

import java.time.LocalDate;

public class Salary implements SuperEntity{
    private String enic;
    private String name;
    private double salary;
    private LocalDate month;
    private LocalDate date;
    private  String status;

    public Salary() {
    }
    public Salary(String enic, String name, double salary, LocalDate month, LocalDate date, String status) {
        this.enic = enic;
        this.name = name;
        this.salary = salary;
        this.month = month;
        this.date = date;
        this.status = status;
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

    @Override
    public String toString() {
        return "Salary{" +
                "enic='" + enic + '\'' +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", month=" + month +
                ", date=" + date +
                ", status='" + status + '\'' +
                '}';
    }
}
