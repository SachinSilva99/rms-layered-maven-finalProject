package com.sachin.finalproject.model;

import com.sachin.finalproject.to.Item;
import com.sachin.finalproject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemModel {



    public static ArrayList<Item> getFood(String category,String subCategory) throws SQLException, ClassNotFoundException {
        ArrayList<Item> items = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM item WHERE category = ? AND subcategory = ?",category,subCategory);
        setItem(items, rst);
        return items;
    }

    private static void setItem(ArrayList<Item> items, ResultSet rst) throws SQLException {
        while(rst.next()){
            Item item = new Item();
            item.setId( rst.getString(1));
            item.setDes( rst.getString(2));
            item.setCategory( rst.getString(3));
            item.setSubCategory( rst.getString(4));
            item.setQtyOnHand(Integer.parseInt(rst.getString(5)));
            item.setPrice(Double.parseDouble((rst.getString(6))));
            item.setSize((rst.getString(7)));
            items.add(item);
        }
    }

    public static ArrayList<Item> getFood() throws SQLException, ClassNotFoundException {
        ArrayList<Item> items = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM item WHERE category = 'food'");
        setItem(items,rst);
        return items;
    }

    public static ArrayList<Item> getDrinkHot(String category, String subcategory) throws SQLException, ClassNotFoundException {
        ArrayList<Item> items = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM item WHERE category = ? AND subcategory = ?",category,subcategory);
        setItem(items,rst);
        return items;
    }

    public static ArrayList<Item> getDrinkSoft(String category, String subcategory) throws SQLException, ClassNotFoundException {
        ArrayList<Item> items = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM item WHERE category = ? AND subcategory = ?",category,subcategory);
        setItem(items,rst);

        return items;
    }

    public static ArrayList<Item> getDrink() throws SQLException, ClassNotFoundException {
        ArrayList<Item> items = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM item WHERE category = 'drink'");
        setItem(items,rst);
        return items;
    }

    public static ArrayList<Item> getDessertAll() throws SQLException, ClassNotFoundException {
        ArrayList<Item> items = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM item WHERE category = 'dessert'");
        setItem(items,rst);
        return items;
    }



    public static ArrayList<Item> getDessertIceCream(String category, String subcategory) throws SQLException, ClassNotFoundException {
        ArrayList<Item> items = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM item WHERE category = ? AND subcategory = ?",category,subcategory);
        setItem(items,rst);
        return items;
    }

    public static ArrayList<Item> getDessertPudding(String category, String subcategory) throws SQLException, ClassNotFoundException {
        ArrayList<Item> items = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM item WHERE category = ? AND subcategory = ?",category,subcategory);
        setItem(items,rst);
        return items;
    }

    public static ArrayList<Item> search(String text) throws SQLException, ClassNotFoundException {

        ArrayList<Item> items = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * from item where category='food'");
        setItem(items,rst,text);
        return items;
    }

    public static ArrayList<Item> drinksearch(String text) throws SQLException, ClassNotFoundException {
        ArrayList<Item> items = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * from item where category='drink'");
        setItem(items,rst,text);
        return items;
    }

    public static ArrayList<Item> dessertsearch(String text) throws SQLException, ClassNotFoundException {
        ArrayList<Item> items = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * from item where category='dessert'");
        setItem(items,rst,text);
        return items;
    }
    private static void setItem(ArrayList<Item> items, ResultSet rst, String text) throws SQLException {
        while(rst.next()){
            Item item = new Item();
            item.setId( rst.getString(1));
            item.setDes( rst.getString(2));
            item.setCategory( rst.getString(3));
            item.setSubCategory( rst.getString(4));
            item.setQtyOnHand(Integer.parseInt(rst.getString(5)));
            item.setPrice(Double.parseDouble((rst.getString(6))));
            item.setSize((rst.getString(7)));
            if(item.getDes().toLowerCase().contains(text)){
                items.add(item);
            }

        }
    }


    public static Item getItem(String itemId) throws SQLException, ClassNotFoundException {
        ResultSet rst  = CrudUtil.execute("SELECT * FROM Item WHERE id = ?",itemId);
        if(rst.next()){
            Item item = new Item();
            item.setId( rst.getString(1));
            item.setDes( rst.getString(2));
            item.setCategory( rst.getString(3));
            item.setSubCategory( rst.getString(4));
            item.setQtyOnHand(Integer.parseInt(rst.getString(5)));
            item.setPrice(Double.parseDouble((rst.getString(6))));
            item.setSize((rst.getString(7)));
            return  item;
        }
        return null;
    }

    public static boolean updateQty(int newRemainingQty, String itemId) throws SQLException, ClassNotFoundException {
        String  sql = "UPDATE item SET `qtyOnHand` = ? WHERE `id` = ?";
        return CrudUtil.execute(sql, newRemainingQty, itemId);
    }

    public static boolean deleteItem(String itemId) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM item WHERE id = ?;", itemId);
    }

    public static boolean addNewFood(String category, String subCategory, String name) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO food VALUES (?,?,?)",name,category,subCategory);
    }
}
