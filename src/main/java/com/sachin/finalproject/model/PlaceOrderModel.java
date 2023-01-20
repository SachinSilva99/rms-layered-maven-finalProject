package com.sachin.finalproject.model;

import com.sachin.finalproject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaceOrderModel {
    public static String generateId(String startName, String id) {
        if (id != null) {
            String[] newId = id.split(startName);
            int addId = Integer.parseInt(newId[1]);
            addId++;
            return String.format(startName + "%05d", addId);
        }

        return startName + "00000";

    }

    public static String getOrderId() {
        try {
            ResultSet rst = CrudUtil.execute("SELECT  * FROM orders ORDER BY id DESC LIMIT 1");
            if (rst.next()) {
                return rst.getString(1);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static boolean intoOrderDetail(String orderId, String itemId, int qty, double price) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO orderDetail VALUES(?,?,?,?)";
        return CrudUtil.execute(sql, orderId, itemId, qty, price);
    }

    public static boolean intoOrders(String orderId, String date, String customerId, String orderType) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO orders VALUES(?,?,?,?)";
        return CrudUtil.execute(sql, orderId, date, customerId, orderType);
    }
}
