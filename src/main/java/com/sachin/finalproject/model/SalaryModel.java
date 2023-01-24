package com.sachin.finalproject.model;

import com.sachin.finalproject.to.Salary;
import com.sachin.finalproject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SalaryModel {
    public static boolean addSalary(String nic, String name, String role, double salary, LocalDate date, String status) throws SQLException, ClassNotFoundException {
       return CrudUtil.execute("INSERT INTO salary (`enic`,`ename`,`esalary`,`month`,`date`,`status`) VALUES (?,?,?,?,?,?)",nic,name,salary,date,date,status);
    }

    public static ArrayList<Salary> getAll() throws SQLException, ClassNotFoundException {
       ResultSet rst =  CrudUtil.execute("SELECT * FROM Salary");
        ArrayList <Salary> salaries = new ArrayList <>();

        while(rst.next()){
            Salary s = new Salary();
            s.setEnic(rst.getString(1));
            s.setName(rst.getString(2));
            s.setSalary(rst.getDouble(3));
            LocalDate month = rst.getDate(4).toLocalDate();
            s.setMonth(month);
            LocalDate date = rst.getDate(5).toLocalDate();
            s.setDate(date);
            salaries.add(s);
        }
        return salaries;
    }
    public static ArrayList<Salary> getAllByDate(int month, int year) throws SQLException, ClassNotFoundException {
        ResultSet rst =  CrudUtil.execute("SELECT * FROM salary WHERE month(month) = ? AND year(month) = ?", month, year);
        ArrayList <Salary> salaries = new ArrayList <>();

        while(rst.next()){
            Salary s = new Salary();
            s.setEnic(rst.getString(1));
            s.setName(rst.getString(2));
            s.setSalary(rst.getDouble(3));
            LocalDate monthh = rst.getDate(4).toLocalDate();
            s.setMonth(monthh);
            LocalDate date = rst.getDate(5).toLocalDate();
            s.setDate(date);
            s.setStatus("Paid");
            salaries.add(s);
        }
        return salaries;
    }
}
