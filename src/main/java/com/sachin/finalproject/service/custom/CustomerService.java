package com.sachin.finalproject.service.custom;

import com.sachin.finalproject.dto.CustomerDTO;
import com.sachin.finalproject.entity.Customer;
import com.sachin.finalproject.service.SuperService;
import com.sachin.finalproject.service.exception.DuplicateException;
import com.sachin.finalproject.service.exception.InUseException;
import com.sachin.finalproject.service.exception.NotFoundException;

import java.util.List;

public interface CustomerService extends SuperService {
    CustomerDTO getCustomerById(String id)throws NotFoundException;
    List<CustomerDTO> getAllCustomer();
    CustomerDTO saveCustomer(CustomerDTO customerDTO)throws DuplicateException;
    String getLastCustomer()throws NotFoundException;
    String generateCustomerId(String startLetter, String id);
    void deleteCustomer(String id)throws NotFoundException, InUseException;
    CustomerDTO updateCustomer(CustomerDTO customerDTO)throws NotFoundException;


}
