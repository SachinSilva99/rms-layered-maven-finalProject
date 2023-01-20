package com.sachin.finalproject.dao.custom.impl;

import com.sachin.finalproject.dao.custom.OrderDetailDAO;
import com.sachin.finalproject.dao.exception.ConstraintViolationException;
import com.sachin.finalproject.entity.OrderDetail;
import com.sachin.finalproject.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    private Connection connection;

    public OrderDetailDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public OrderDetail save(OrderDetail od) throws ConstraintViolationException {
        boolean isSaved = CrudUtil.execute("INSERT INTO orderdetail VALUES (orderId,itemID,qty,unitPrice)",
                od.getOrderId(),
                od.getItemID(),
                od.getQty(),
                od.getUnitPrice()
        );
        if (!isSaved) try {
            throw new SQLException("Failed to save the orderDetail");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
        return null;
    }

    @Override
    public OrderDetail update(OrderDetail od) throws ConstraintViolationException {
        boolean isUpdated = CrudUtil.execute("UPDATE orderdetail SET qty = ?, unitPrice = ? WHERE orderId = ? OR itemID = ?",
                od.getQty(),
                od.getUnitPrice(),
                od.getOrderId(),
                od.getItemID()
        );
        if (!isUpdated) try {
            throw new SQLException("Failed to update the order detail");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
        return od;
    }

    @Override
    public void deleteByPk(String pk) throws ConstraintViolationException {
        boolean isDeleted = CrudUtil.execute("DELETE FROM item WHERE id = ?", pk);
        if(!isDeleted){
            try {
                throw new SQLException("Failed to delete item");
            } catch (SQLException e) {
                throw new ConstraintViolationException(e);
            }
        }
    }

    @Override
    public List<OrderDetail> findAll() {
        try {
            ResultSet rs = CrudUtil.execute("SELECT * FROM rms.orderdetail");
            List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
            return getOrderDetails(rs, orderDetails);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public Optional<OrderDetail> findByPk(String pk) {
        try {
            ResultSet rs = CrudUtil.execute("SELECT * FROM item WHERE id = ?", pk);
            List<OrderDetail> orderDetails = getOrderDetails(rs, new ArrayList<OrderDetail>());
            if(orderDetails.isEmpty())return Optional.empty();
            return Optional.ofNullable(orderDetails.get(0));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean existByPk(String pk) {
        ResultSet rs = CrudUtil.execute("SELECT * FROM orderdetail where itemID = ?", pk);
        try {
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long count() {
        try {
            ResultSet rst = CrudUtil.execute("SELECT COUNT(orderId) as orderCount FROM orderdetail");
            if (rst.next()) return rst.getLong("orderCount");
        } catch (SQLException e) {
            throw new RuntimeException("failed to load order detail count");
        }
        return 0;
    }
    private static List<OrderDetail> getOrderDetails(ResultSet rs, List<OrderDetail> orderDetails) throws SQLException {
        while (rs.next()) {
            String orderId = rs.getString("orderId");
            String itemId = rs.getString("itemID");
            int qty = rs.getInt("qty");
            double unitPrice = rs.getDouble("unitPrice");

            OrderDetail orderDetail = new OrderDetail(orderId,itemId,qty,unitPrice);
            orderDetails.add(orderDetail);
        }

        return orderDetails;
    }
}
