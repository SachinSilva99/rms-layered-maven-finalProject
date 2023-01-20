package com.sachin.finalproject.model;

import com.sachin.finalproject.db.DBConnection;
import com.sachin.finalproject.to.Employee;
import com.sachin.finalproject.to.EmployeeTM;
import com.sachin.finalproject.util.CrudUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class EmployeeModel {
    public static ArrayList<Employee> getAllEmployee() throws SQLException, ClassNotFoundException {
        ArrayList <Employee> employees = new ArrayList <>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM Employee");
        rstWhile(employees, rst);
        return employees;
    }

    private static void rstWhile(ArrayList <Employee> employees, ResultSet rst) throws SQLException {
        while (rst.next()){
            Employee e = new Employee();
            e.setNic(rst.getString(1));
            e.setName(rst.getString(2));
            e.setRole(rst.getString(3));
            Date date = rst.getDate(4);
            LocalDate localDate = date.toLocalDate();
            e.setDob(localDate);
            e.setSalary(rst.getDouble(5));
            e.setAddress(rst.getString(6));
            e.setPhoneNumber(rst.getString(7));
            e.setGender(rst.getString(8));
            employees.add(e);
        }
    }
    public static boolean insertEmployee(Employee e) throws SQLException, ClassNotFoundException {
        DBConnection.getInstance().getConnection().setAutoCommit(false);
        String sql = "INSERT INTO `employee` VALUES(?,?,?,?,?,?,?,?)";
        boolean isInserted = CrudUtil.execute(
                sql, e.getNic(), e.getName(), e.getRole(), e.getDob(), e.getSalary(),e.getAddress(),e.getPhoneNumber(),e.getGender());
        if(isInserted){
            DBConnection.getInstance().getConnection().commit();
            return true;
        }
        DBConnection.getInstance().getConnection().rollback();
        return false;
    }

    public static boolean delete(EmployeeTM selectedE) throws SQLException, ClassNotFoundException {
       boolean isDeleted =  CrudUtil.execute("DELETE FROM  employee where nic = ?", selectedE.getNic());
       return isDeleted;

    }

    public static boolean update(Employee e) throws SQLException, ClassNotFoundException {
        String SQL = "UPDATE `employee` SET `nic` = ?, `name` = ?, `role` = ?, `dob` = ?, `salary` = ?, `address` = ?, `phoneNumber` = ?, `gender` = ?WHERE `nic` = ?;";
        return CrudUtil.execute(SQL,e.getNic(),e.getName(),e.getRole(),e.getDob(),e.getSalary(),e.getAddress(),e.getPhoneNumber(),e.getGender(),e.getNic());
    }
}
