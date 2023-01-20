package com.sachin.finalproject.to;

import java.util.Date;

public class OrderDetail {
    private String orderId;
    private int quantity;
    private Date date;
    private String customerId;
    private String orderType;

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderId='" + orderId + '\'' +
                ", quantity=" + quantity +
                ", date=" + date +
                ", customerId='" + customerId + '\'' +
                ", orderType='" + orderType + '\'' +
                '}';
    }

    public OrderDetail(String orderId, int quantity, Date date, String customerId, String orderType) {
        this.orderId = orderId;
        this.quantity = quantity;
        this.date = date;
        this.customerId = customerId;
        this.orderType = orderType;
    }

    public OrderDetail() {}

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
