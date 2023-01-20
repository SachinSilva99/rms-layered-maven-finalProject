package com.sachin.finalproject.dto;

public class FoodDTO implements SuperDTO{

    private String name;
    private String category;
    private String subCategory;

    public FoodDTO() {
    }

    public FoodDTO(String name, String category, String subCategory) {
        this.name = name;
        this.category = category;
        this.subCategory = subCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", subCategory='" + subCategory + '\'' +
                '}';
    }
}
