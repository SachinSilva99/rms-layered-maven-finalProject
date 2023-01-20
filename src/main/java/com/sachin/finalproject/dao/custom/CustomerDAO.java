package com.sachin.finalproject.dao.custom;

import com.sachin.finalproject.dao.CrudDAO;
import com.sachin.finalproject.entity.Customer;

import java.util.Optional;

public interface CustomerDAO extends CrudDAO<Customer, String> {
    Optional<String> getLastCustomerId();
}
