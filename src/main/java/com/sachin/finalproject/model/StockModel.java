package com.sachin.finalproject.model;

import com.sachin.finalproject.to.Item;
import com.sachin.finalproject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockModel {
    public static ArrayList<String> getAllNames(String category, String subCategory) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Food WHERE category = ? AND subCategory = ?";
        ResultSet rst = CrudUtil.execute(sql, category, subCategory);
        ArrayList <String> names = new ArrayList <>();
        while (rst.next()){
            String name = rst.getString(1);
            names.add(name);
        }
        return names;
    }

    public static Item getItem(String name) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Item WHERE id = ?";
        Item item = new Item();
        ResultSet rst = CrudUtil.execute(sql, name);
        if(rst.next()){

            item.setId( rst.getString(1).toLowerCase());
            item.setDes( rst.getString(2));
            item.setCategory( rst.getString(3));
            item.setSubCategory( rst.getString(4));
            item.setQtyOnHand(Integer.parseInt(rst.getString(5)));
            item.setPrice(Double.parseDouble((rst.getString(6))));
            item.setSize((rst.getString(7)));
            return item;
        }
        return null;
    }

    public static String getId(String name) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Food WHERE name = ?";
        ResultSet rst = CrudUtil.execute(sql, name);
        if(rst.next()){
            return rst.getString(1);
        }
        return "";
    }

    public static boolean addItem(Item item) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO `rms`.`item` (`id`, `des`, `category`, `subcategory`, `qtyOnHand`, `price`, `size`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?);";
        return CrudUtil.execute(
                sql,item.getId(),item.getDes(),
                item.getCategory(),item.getSubCategory(),
                item.getQtyOnHand(),item.getPrice(),
                item.getSize()
        );
    }

    public static boolean updateItem(Item i) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE `item` SET `des` = ?, `category` = ?, `subcategory` = ?, `qtyOnHand` = ?, `price` = ?, `size` =? WHERE `id` = ?;";
        return CrudUtil.execute(sql,i.getDes(),i.getCategory(),i.getSubCategory(),i.getQtyOnHand(),i.getPrice(),i.getSize(),i.getId());
    }

    public static ArrayList<Item> getAllItems() throws SQLException, ClassNotFoundException {
        String sql= "SELECT * FROM Item";
        ResultSet rst = CrudUtil.execute(sql);
        ArrayList <Item> items = new ArrayList <>();
        while (rst.next()){
            Item item  = new Item();
            item.setId( rst.getString(1).toLowerCase());
            item.setDes( rst.getString(2));
            item.setCategory( rst.getString(3));
            item.setSubCategory( rst.getString(4));
            item.setQtyOnHand(Integer.parseInt(rst.getString(5)));
            item.setPrice(Double.parseDouble((rst.getString(6))));
            item.setSize((rst.getString(7)));
            items.add(item);
        }
        return items;
    }


}
