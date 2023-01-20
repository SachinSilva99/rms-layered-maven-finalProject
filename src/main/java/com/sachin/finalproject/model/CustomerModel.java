package com.sachin.finalproject.model;

import com.sachin.finalproject.db.DBConnection;
import com.sachin.finalproject.to.Customer;
import com.sachin.finalproject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class CustomerModel {
    public static boolean updateCustomer(Customer c) throws SQLException, ClassNotFoundException {
        DBConnection.getInstance().getConnection().setAutoCommit(false);
        String SQL = "UPDATE `customer`SET`phoneNumber` = ?,`name` = ?,`id` = ?, `address` = ? WHERE `id` = ?;";
        boolean success =  CrudUtil.execute(SQL,c.getPhoneNumber(),c.getName(),c.getId(),c.getAddress(),c.getId());
        if(success){
            DBConnection.getInstance().getConnection().commit();
            return  true;
        }
        DBConnection.getInstance().getConnection().rollback();
        DBConnection.getInstance().getConnection().setAutoCommit(true);
        return false;

    }

    public static ArrayList<Customer> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList <Customer> customers = new ArrayList <>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM Customer");
        while(rst.next()){
            Customer c = new Customer();
            c.setPhoneNumber(rst.getString(1));
            c.setName(rst.getString(2));
            c.setAddress(rst.getString(3));
            c.setId(rst.getString(4));
            c.setGender(rst.getString(5));
            customers.add(c);
        }
        return customers;
    }

    public static boolean delete(String id) throws SQLException, ClassNotFoundException {
        boolean isDeleted =  CrudUtil.execute("DELETE FROM  customer where id = ?", id);
        return isDeleted;
    }
    public static String generateCustomerId(String startName, String id) {
        if (id != null) {
            String[] newId = id.split(startName);
            System.out.println(Arrays.toString(newId));
            int addId = Integer.parseInt(newId[1]);
            addId++;
            return String.format(startName + "%05d", addId);
        }
        return startName + "00001";
    }

    public static String getCurrentCustomer() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Customer ORDER BY id DESC LIMIT 1");
        if(rst.next()){
            return rst.getString(4);
        }
        return null;
    }

    public static boolean insertC(String id, String name, String phoneN, String gender, String address) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Customer VALUES(?,?,?,?,?)",phoneN,name,address,id,gender);
    }
}
