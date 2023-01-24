package com.sachin.finalproject.service.custom.impl;

import com.sachin.finalproject.dao.CrudDAO;
import com.sachin.finalproject.dao.DaoFactory;
import com.sachin.finalproject.dao.DaoType;
import com.sachin.finalproject.dao.custom.OrderDetailDAO;
import com.sachin.finalproject.dao.custom.OrdersDAO;
import com.sachin.finalproject.db.DBConnection;
import com.sachin.finalproject.dto.OrderDetailDTO;
import com.sachin.finalproject.dto.OrdersDTO;
import com.sachin.finalproject.entity.Orders;
import com.sachin.finalproject.service.custom.OrderService;
import com.sachin.finalproject.service.util.Converter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    private final OrdersDAO ordersDAO ;
    private final OrderDetailDAO orderDetailDAO ;
    private final Connection connection;
    private final Converter converter;

    public OrderServiceImpl() {
        this.connection = DBConnection.getInstance().getConnection();
        this.ordersDAO = DaoFactory.getInstance().getDao(connection, DaoType.ORDERS);
        this.orderDetailDAO = DaoFactory.getInstance().getDao(connection, DaoType.ORDER_DETAIL);

        this.converter = new Converter();
    }

    @Override
    public String generateId(String id) {
        if (id != null) {
            String[] newId = id.split("D");
            int addId = Integer.parseInt(newId[1]);
            addId++;
            return String.format("C" + "%05d", addId);
        }
        return "D" + "00000";
    }

    @Override
    public Optional<String> getOrderId() {
        return ordersDAO.getLastOrderId();
    }

    @Override
    public boolean placeOrder(OrdersDTO ordersDTO, ArrayList<OrderDetailDTO> orderDetailDTOS) {
        try {
            connection.setAutoCommit(false);
            Orders save = ordersDAO.save(converter.toOrders(ordersDTO));
            for (OrderDetailDTO od : orderDetailDTOS) {
                orderDetailDAO.save(converter.toOrderDetail(od));
            }
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }
}
