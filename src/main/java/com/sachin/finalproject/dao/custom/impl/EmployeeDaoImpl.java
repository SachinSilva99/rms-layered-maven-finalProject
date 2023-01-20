package com.sachin.finalproject.dao.custom.impl;


import com.sachin.finalproject.dao.custom.EmployeeDAO;
import com.sachin.finalproject.dao.exception.ConstraintViolationException;
import com.sachin.finalproject.entity.Employee;
import com.sachin.finalproject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDaoImpl implements EmployeeDAO {

    @Override
    public Employee save(Employee employee) throws ConstraintViolationException {
        boolean isSaved = CrudUtil.execute("INSERT INTO employee VALUES (?,?,?,?,?,?,?,?)",
                employee.getNic(),
                employee.getName(),
                employee.getRole(),
                employee.getDob(),
                employee.getSalary(),
                employee.getAddress(),
                employee.getPhoneNumber(),
                employee.getGender()
        );
        if(!isSaved){
            throw new ConstraintViolationException("Couldn't save employee");
        }
        return employee;
    }

    @Override
    public Employee update(Employee employee) throws ConstraintViolationException {
        boolean isUpdated = CrudUtil.execute("UPDATE employee SET name = ?, role = ?, dob = ?, salary = ?, address = ?, phoneNumber = ?, gender = ? WHERE nic = ?",
                employee.getName(),
                employee.getRole(),
                employee.getDob(),
                employee.getSalary(),
                employee.getAddress(),
                employee.getPhoneNumber(),
                employee.getGender(),
                employee.getNic()
                );
        if(!isUpdated){
            throw new ConstraintViolationException("Failed to update employee");
        }
        return employee;
    }

    @Override
    public void deleteByPk(String pk) throws ConstraintViolationException {
        if(CrudUtil.execute("DELETE FROM employee WHERE nic = ?", pk)){
            return;
        }
        throw new ConstraintViolationException("Failed to delete");
    }

    @Override
    public List<Employee> findAll() {
        try {

            ResultSet rst = CrudUtil.execute("SELECT  * FROM employee");
            return getEmployees(rst);
        }catch (SQLException e){
            throw new RuntimeException("Failed to load the employees");
        }
    }


    @Override
    public Optional<Employee> findByPk(String pk) {
        ResultSet rst = CrudUtil.execute("SELECT * FROM employee WHERE nic = ?", pk);
        try {
            ArrayList<Employee> employees = getEmployees(rst);
            if(employees.isEmpty()){
                return Optional.empty();
            }
            return Optional.of(employees.get(0));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existByPk(String pk) {
        return CrudUtil.execute("SELECT * FROM employee where nic = ?", pk);
    }

    @Override
    public long count() {
        try {
            ResultSet rst = CrudUtil.execute("SELECT COUNT(nic) as employeeCount FROM employee");
            if(rst.next())return rst.getLong("employeeCount");
        } catch (SQLException e) {
            throw new RuntimeException("failed to load employee count");
        }
        return 0;
    }
    private static ArrayList<Employee> getEmployees(ResultSet rst) throws SQLException {
        ArrayList<Employee> employees = new ArrayList<>();
        while (rst.next()){
            Employee employee = new Employee(
                    rst.getString("phoneNumber"),
                    rst.getString("nic"),
                    rst.getString("name"),
                    rst.getString("role"),
                    rst.getDouble("salary"),
                    rst.getDate("dob").toLocalDate(),
                    rst.getString("address"),
                    rst.getString("gender")
            );
            employees.add(employee);
        }
        return employees;
    }
}
