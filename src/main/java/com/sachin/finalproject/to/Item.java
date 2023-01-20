package com.sachin.finalproject.to;

import java.awt.*;

public class Item {
    private int qty;
    private String id;
    private int qtyOnHand;
    private String des;
    private double price;
    private String category;
    private String subCategory;
    private String size;


    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", qtyOnHand=" + qtyOnHand +
                ", des='" + des + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", subCategory='" + subCategory + '\'' +
                ", size='" + size + '\'' +
                '}';
    }

    public Item() {
    }

    public Item(int qty, String id, int qtyOnHand, String des, double price, String category, String subCategory, String size) {
        this.qty = qty;
        this.id = id;
        this.qtyOnHand = qtyOnHand;
        this.des = des;
        this.price = price;
        this.category = category;
        this.subCategory = subCategory;
        this.size = size;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
