package com.sachin.finalproject.service.custom;

import com.sachin.finalproject.dto.EmployeeDTO;
import com.sachin.finalproject.service.SuperService;
import com.sachin.finalproject.service.exception.DuplicateException;
import com.sachin.finalproject.to.Employee;

import java.util.List;

public interface EmployeeService extends SuperService {
    Employee saveEmployee(EmployeeDTO employeeDTO)throws DuplicateException;
    List<EmployeeDTO> getAll();

}
