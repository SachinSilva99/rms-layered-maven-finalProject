package com.sachin.finalproject.to;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Button;

public class OrderTm {
    private int qtyOnHand;
    private String size;
    private String code;
    private String name;
    private int qty;
    private double price;
    private double total;

    private JFXButton button;
    private JFXButton plusBtn;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public JFXButton getPlusBtn() {
        return plusBtn;
    }

    public void setPlusBtn(JFXButton plusBtn) {
        this.plusBtn = plusBtn;
    }

    public OrderTm() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public OrderTm(int qtyOnHand, String size, String code, String name, int qty, double price, double total, JFXButton button, JFXButton plusBtn) {
        this.qtyOnHand = qtyOnHand;
        this.size = size;
        this.code = code;
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.total = total;
        this.button = button;
        this.plusBtn = plusBtn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(JFXButton button) {
        this.button = button;
    }
    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }
}
