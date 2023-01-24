package com.sachin.finalproject.service.custom.impl;

import com.sachin.finalproject.dao.DaoFactory;
import com.sachin.finalproject.dao.DaoType;
import com.sachin.finalproject.dao.custom.SalaryDAO;
import com.sachin.finalproject.db.DBConnection;
import com.sachin.finalproject.dto.SalaryDTO;
import com.sachin.finalproject.service.custom.SalaryService;
import com.sachin.finalproject.service.exception.DuplicateException;
import com.sachin.finalproject.service.util.Converter;
import com.sachin.finalproject.to.Salary;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SalaryServiceImpl implements SalaryService {
    private final Connection connection = DBConnection.getInstance().getConnection();
    private final SalaryDAO salaryDAO = DaoFactory.getInstance().getDao(connection, DaoType.SALARY);
    private final Converter converter = new Converter();
    @Override
    public List<SalaryDTO> getAll() {
       return salaryDAO.findAll().stream().map(salary -> converter.fromSalary(salary)).collect(Collectors.toList());
    }

    @Override
    public SalaryDTO saveSalary(SalaryDTO salaryDTO) throws DuplicateException {
        if(salaryDAO.existByPk(salaryDTO.getId()))throw new DuplicateException("Salary id already used");
        salaryDAO.save(converter.toSalary(salaryDTO));
        return salaryDTO;
    }

    @Override
    public ArrayList<SalaryDTO> getAllByDate(int month, int year) {
        return (ArrayList<SalaryDTO>) salaryDAO.getAllByDate(month,year).stream().map(salary ->
                converter.fromSalary(salary)).collect(Collectors.toList());
    }
}
