package com.sachin.finalproject.dao.custom;

import com.sachin.finalproject.dao.CrudDAO;
import com.sachin.finalproject.entity.Salary;

import java.util.List;

public interface SalaryDAO extends CrudDAO<Salary,Integer> {
    List<Salary> getAllByDate(int month, int year);
}
