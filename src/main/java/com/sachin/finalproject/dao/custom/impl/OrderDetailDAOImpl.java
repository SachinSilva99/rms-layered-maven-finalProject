package com.sachin.finalproject.dao.custom.impl;

import com.sachin.finalproject.dao.custom.OrderDetailDAO;
import com.sachin.finalproject.dao.exception.ConstraintViolationException;
import com.sachin.finalproject.entity.OrderDetail;

import java.util.List;
import java.util.Optional;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public OrderDetail save(OrderDetail orderDetail) throws ConstraintViolationException {
        return null;
    }

    @Override
    public OrderDetail update(OrderDetail orderDetail) throws ConstraintViolationException {
        return null;
    }

    @Override
    public void deleteByPk(String pk) throws ConstraintViolationException {

    }

    @Override
    public List<OrderDetail> findAll() {
        return null;
    }

    @Override
    public Optional<OrderDetail> findByPk(String pk) {
        return Optional.empty();
    }

    @Override
    public boolean existByPk(String pk) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }
}
