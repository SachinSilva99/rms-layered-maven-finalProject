package com.sachin.finalproject.service.custom;

import com.sachin.finalproject.dto.EmployeeDTO;
import com.sachin.finalproject.service.SuperService;
import com.sachin.finalproject.service.exception.DuplicateException;
import com.sachin.finalproject.service.exception.InUseException;
import com.sachin.finalproject.service.exception.NotFoundException;

import java.util.List;

public interface EmployeeService extends SuperService {
    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO)throws DuplicateException;
    List<EmployeeDTO> getAll();

    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO)throws NotFoundException;
    void deleteEmployee(String id)throws NotFoundException, InUseException;


}
