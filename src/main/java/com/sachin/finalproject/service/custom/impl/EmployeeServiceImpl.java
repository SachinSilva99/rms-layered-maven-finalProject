package com.sachin.finalproject.service.custom.impl;

import com.sachin.finalproject.dao.DaoFactory;
import com.sachin.finalproject.dao.DaoType;
import com.sachin.finalproject.dao.custom.EmployeeDAO;
import com.sachin.finalproject.dao.exception.ConstraintViolationException;
import com.sachin.finalproject.db.DBConnection;
import com.sachin.finalproject.dto.EmployeeDTO;
import com.sachin.finalproject.entity.Employee;
import com.sachin.finalproject.service.custom.EmployeeService;
import com.sachin.finalproject.service.exception.DuplicateException;
import com.sachin.finalproject.service.exception.InUseException;
import com.sachin.finalproject.service.exception.NotFoundException;
import com.sachin.finalproject.service.util.Converter;

import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {
    private final Connection connection;
    private final EmployeeDAO employeeDAO;
    private final Converter converter;

    public EmployeeServiceImpl() {
        this.connection = DBConnection.getInstance().getConnection();
        this.employeeDAO = DaoFactory.getInstance().getDao(connection, DaoType.EMPLOYEE);
        this.converter = new Converter();
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) throws DuplicateException {
        if(employeeDAO.existByPk(employeeDTO.getNic()))throw new DuplicateException("Employee already exists");
        employeeDAO.save(converter.toEmployee(employeeDTO));
        return employeeDTO;

    }

    @Override
    public List<EmployeeDTO> getAll() {
        List<Employee> employees = employeeDAO.findAll();
        return employees.stream().map(employee -> converter.fromEmployee(employee)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) throws NotFoundException {
        if(!employeeDAO.existByPk(employeeDTO.getNic())) throw new NotFoundException();
        employeeDAO.update(converter.toEmployee(employeeDTO));
        return employeeDTO;
    }

    @Override
    public void deleteEmployee(String id) throws NotFoundException, InUseException {
        if(!employeeDAO.existByPk(id))throw new NotFoundException("Employee not found");
        try {
            employeeDAO.deleteByPk(id);
        }catch (ConstraintViolationException e){
            throw new InUseException("In use employee");
        }
    }
}
