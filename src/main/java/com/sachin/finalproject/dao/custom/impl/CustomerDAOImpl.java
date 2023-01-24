package com.sachin.finalproject.dao.custom.impl;

import com.sachin.finalproject.dao.custom.CustomerDAO;
import com.sachin.finalproject.dao.exception.ConstraintViolationException;
import com.sachin.finalproject.entity.Customer;
import com.sachin.finalproject.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDAOImpl implements CustomerDAO {
    private Connection connection;
  /*public static void main(String[] args) {
        CustomerDAOImpl customerDAO = new CustomerDAOImpl();
       *//* Customer save = customerDAO.save(new Customer("sac", "male", "Aluthgama", "s000001", "077"));
        if(save!=null){
            System.out.println("saved" + save);
        }*//**//*
       /* List<Customer> all = customerDAO.findAll();
        System.out.println(all);*//*
      System.out.println(customerDAO.count());
  }*/

    public CustomerDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Customer save(Customer customer) throws ConstraintViolationException {
        try {
            if(CrudUtil.execute("INSERT INTO customer VALUES (?,?,?,?,?)",
                    customer.getPhoneNumber(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getId(),
                    customer.getGender()
                    )){
                return customer;
            }
            throw new SQLException("Failed to save the book");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
    }

    @Override
    public Customer update(Customer customer) throws ConstraintViolationException {
        try {
            if(CrudUtil.execute("UPDATE customer SET phoneNumber = ?, name = ?, address = ?, gender = ? WHERE id = ?",
                    customer.getPhoneNumber(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getGender(),
                    customer.getId()
            )){
                return customer;
            }
            throw new SQLException("Failed to update the book");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e.getMessage());
        }
    }

    @Override
    public void deleteByPk(String pk) throws ConstraintViolationException {
        try {
            if(!(Boolean) CrudUtil.execute("DELETE FROM customer WHERE id = ?",pk)){
                throw new SQLException("Failed to delete the book");
            }
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
    }

    @Override
    public List<Customer> findAll() {
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            ResultSet rst = CrudUtil.execute("SELECT  * FROM customer");
            return getCustomers(customers, rst);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to load customers");
        }
    }

    @Override
    public Optional<Customer> findByPk(String pk) {
        try {
            ResultSet rst = CrudUtil.execute("SELECT * FROM customer WHERE id=?", pk);
            if(rst.next()){
                Customer customer = new Customer(
                        rst.getString("name"),
                        rst.getString("gender"),
                        rst.getString("address"),
                        rst.getString("id"),
                        rst.getString("phoneNumber")
                );
                return Optional.of(customer);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the customer details");
        }

    }

    @Override
    public boolean existByPk(String pk) {
        try {
            ResultSet rst = CrudUtil.execute("SELECT * FROM customer WHERE id=?", pk);
            return rst.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public long count() {
        try {
            ResultSet rst = CrudUtil.execute("SELECT COUNT(id) as customerCount FROM customer");
            if(rst.next())return rst.getLong("customerCount");
        } catch (SQLException e) {
            throw new RuntimeException("failed to load customer count");
        }
        return 0;
    }
    private static ArrayList<Customer> getCustomers(ArrayList<Customer> customers, ResultSet rst) throws SQLException {
        while (rst.next()){
            Customer customer = new Customer(
                    rst.getString("name"),
                    rst.getString("gender"),
                    rst.getString("address"),
                    rst.getString("id"),
                    rst.getString("phoneNumber")
            );
            customers.add(customer);
        }
        return customers;
    }

    @Override
    public Optional<String> getLastCustomerId() {
        try {
            ResultSet rst = CrudUtil.execute("SELECT * FROM Customer ORDER BY id DESC LIMIT 1");
            if(rst.next()){
                return Optional.of(rst.getString(4));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
