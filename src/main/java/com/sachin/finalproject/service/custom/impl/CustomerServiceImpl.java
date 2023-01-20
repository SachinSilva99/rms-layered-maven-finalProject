package com.sachin.finalproject.service.custom.impl;

import com.sachin.finalproject.dao.DaoFactory;
import com.sachin.finalproject.dao.DaoType;
import com.sachin.finalproject.dao.custom.CustomerDAO;
import com.sachin.finalproject.dao.exception.ConstraintViolationException;
import com.sachin.finalproject.db.DBConnection;
import com.sachin.finalproject.dto.CustomerDTO;
import com.sachin.finalproject.entity.Customer;
import com.sachin.finalproject.service.custom.CustomerService;
import com.sachin.finalproject.service.exception.DuplicateException;
import com.sachin.finalproject.service.exception.InUseException;
import com.sachin.finalproject.service.exception.NotFoundException;
import com.sachin.finalproject.service.util.Converter;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {
    private final Connection connection;
    private final CustomerDAO customerDAO;
    private final Converter converter;

    public CustomerServiceImpl() {
        this.connection = DBConnection.getInstance().getConnection();
        this.customerDAO = DaoFactory.getInstance().getDao(connection, DaoType.CUSTOMER);
        this.converter = new Converter();
    }

    @Override
    public CustomerDTO getCustomerById(String id) throws NotFoundException {
        Optional<Customer> customer = customerDAO.findByPk(id);
        if(customer.isEmpty())throw new NotFoundException(id +" Customer is not found");
        return converter.fromCustomer(customer.get());
    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
        return customerDAO.findAll().stream().map(customer -> converter.fromCustomer(customer)).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) throws DuplicateException {
        if(customerDAO.existByPk(customerDTO.getId()))throw new DuplicateException("Customer Already exists");
        Customer customer = customerDAO.save(converter.toCustomer(customerDTO));
        return converter.fromCustomer(customer);
    }

    @Override
    public String getLastCustomer() throws NotFoundException {
        Optional<String> lastCustomerId = customerDAO.getLastCustomerId();
        if(lastCustomerId.isEmpty())throw new NotFoundException("Last Customer id not found");
        return lastCustomerId.get();
    }

    @Override
    public String generateCustomerId(String startLetter, String id) {
        if (id != null) {
            String[] newId = id.split(startLetter);
            System.out.println(Arrays.toString(newId));
            int addId = Integer.parseInt(newId[1]);
            addId++;
            return String.format(startLetter + "%05d", addId);
        }
        return startLetter + "00001";
    }

    @Override
    public void deleteCustomer(String id) throws NotFoundException, InUseException {
        if(!customerDAO.existByPk(id))throw new  NotFoundException(id + "customer not found");
        try {
            customerDAO.deleteByPk(id);
        }catch (ConstraintViolationException e){
            throw new InUseException("customer in use");
        }
    }
}
