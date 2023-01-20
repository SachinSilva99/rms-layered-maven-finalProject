package com.sachin.finalproject.to;

import java.util.Date;

public class Payment {
    Date date;
    double total;

    public Payment(Date date, double total) {
        this.date = date;
        this.total = total;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "date=" + date +
                ", total=" + total +
                '}';
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Payment() {
    }
}
