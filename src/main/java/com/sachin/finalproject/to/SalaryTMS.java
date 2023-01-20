package com.sachin.finalproject.to;

public class SalaryTMS {
    private String nic;
    private String name;
    private String salary;
    private String status;
    private String month;
    private String date;

    public SalaryTMS(String nic, String name, String salary, String status, String month, String date) {
        this.setNic(nic);
        this.setName(name);
        this.setSalary(salary);
        this.setStatus(status);
        this.setMonth(month);
        this.setDate(date);
    }

    public SalaryTMS() {
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

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
