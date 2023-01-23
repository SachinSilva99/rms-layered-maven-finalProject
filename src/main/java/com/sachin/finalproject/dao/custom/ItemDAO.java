package com.sachin.finalproject.dao.custom;

import com.sachin.finalproject.dao.CrudDAO;
import com.sachin.finalproject.entity.Item;

import java.util.List;

public interface ItemDAO extends CrudDAO<Item, String> {
    List<Item> getFoodCategoryAll();
    List<Item> getFoodSubcategoryCategory(String category,String subCategory);
    List<Item> getFoodOnlyCategory(String category);
    boolean updateQty(int newRemainingQty, String itemId);
}
