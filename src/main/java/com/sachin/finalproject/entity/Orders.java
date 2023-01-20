package com.sachin.finalproject.entity;

import java.time.LocalDate;

public class Orders implements SuperEntity{
    private String id;
    private LocalDate date;
    private String CustomerId;
    private String orderType;

    public Orders() {
    }

    public Orders(String id, LocalDate date, String customerId, String orderType) {
        this.id = id;
        this.date = date;
        CustomerId = customerId;
        this.orderType = orderType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", CustomerId='" + CustomerId + '\'' +
                ", orderType='" + orderType + '\'' +
                '}';
    }
}
