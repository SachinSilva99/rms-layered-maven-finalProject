package com.sachin.finalproject.service.custom;

import com.sachin.finalproject.dto.SalaryDTO;
import com.sachin.finalproject.service.SuperService;
import com.sachin.finalproject.service.exception.DuplicateException;
import com.sachin.finalproject.to.Salary;

import java.util.ArrayList;
import java.util.List;

public interface SalaryService extends SuperService {
    List<SalaryDTO> getAll();
    SalaryDTO saveSalary(SalaryDTO salaryDTO)throws DuplicateException;
    ArrayList<SalaryDTO> getAllByDate(int month, int year);
}
