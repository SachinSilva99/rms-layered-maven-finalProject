package com.sachin.finalproject.dao.custom.impl;

import com.sachin.finalproject.dao.custom.OrdersDAO;
import com.sachin.finalproject.dao.exception.ConstraintViolationException;
import com.sachin.finalproject.entity.Orders;
import com.sachin.finalproject.util.CrudUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrdersDAOImpl implements OrdersDAO {
    private Connection connection;

    public OrdersDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Orders save(Orders orders) throws ConstraintViolationException {
        boolean isSaved = CrudUtil.execute("INSERT INTO orders (id, date, customerId, orderType) VALUES (?,?,?,?)",
                orders.getId(),
                orders.getDate(),
                orders.getCustomerId(),
                orders.getOrderType()
        );
        if (!isSaved) try {
            throw new SQLException("Failed to save the orders");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
        return orders;
    }

    @Override
    public Orders update(Orders orders) throws ConstraintViolationException {
        boolean isUpdated = CrudUtil.execute("UPDATE orders SET date = ?, customerId = ?, orderType = ? WHERE id = ?",
                orders.getDate(),
                orders.getCustomerId(),
                orders.getOrderType(),
                orders.getId()
        );
        if (!isUpdated) try {
            throw new SQLException("Failed to update orders");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
        return orders;
    }

    @Override
    public void deleteByPk(String pk) throws ConstraintViolationException {
        boolean isDeleted = CrudUtil.execute("DELETE FROM orders WHERE id = ?", pk);
        if (!isDeleted) try {
            throw new SQLException("Failed to delete the orders");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
    }

    @Override
    public List<Orders> findAll() {
        try {
            ResultSet rs = CrudUtil.execute("SELECT * FROM rms.orders");
            ArrayList<Orders> orders = new ArrayList<>();
            return getOrders(rs, orders);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Optional<Orders> findByPk(String pk) {
        try {
            ResultSet rs = CrudUtil.execute("SELECT * FROM orders WHERE id = ?", pk);
            ArrayList<Orders> orders = getOrders(rs, new ArrayList<Orders>());
            if (orders.isEmpty()) return Optional.empty();
            return Optional.ofNullable(orders.get(0));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean existByPk(String pk) {
        ResultSet rs = CrudUtil.execute("SELECT * FROM orders WHERE id = ?", pk);
        try {
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long count() {
        try {
            ResultSet rst = CrudUtil.execute("SELECT COUNT(id) as orderCount FROM orders");
            if (rst.next()) return rst.getLong("orderCount");
        } catch (SQLException e) {
            throw new RuntimeException("failed to load order count");
        }
        return 0;
    }


    private static ArrayList<Orders> getOrders(ResultSet rs, ArrayList<Orders> orders) throws SQLException {
        while (rs.next()) {
            String id = rs.getString("id");
            Date date = rs.getDate("date");
            String customerId = rs.getString("customerId");
            String orderType = rs.getString("orderType");

            Orders order = new Orders(id, date.toLocalDate(), customerId, orderType);
            orders.add(order);
        }
        return orders;
    }

    @Override
    public Optional<String> getLastOrderId() {
        try {
            ResultSet rst = CrudUtil.execute("SELECT  * FROM orders ORDER BY id DESC LIMIT 1");
            if (rst.next()) {
                return Optional.ofNullable(rst.getString(1));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
