package com.sachin.finalproject.to;

import java.util.Date;

public class SalaryDetail {
    String id;
    double salary;
    String employeeId;
    Date date;

    public SalaryDetail(String id, double salary, String employeeId, Date date) {
        this.id = id;
        this.salary = salary;
        this.employeeId = employeeId;
        this.date = date;
    }

    public SalaryDetail() {
    }

    @Override
    public String toString() {
        return "SalaryDetail{" +
                "id='" + id + '\'' +
                ", salary=" + salary +
                ", employeeId='" + employeeId + '\'' +
                ", date=" + date +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
