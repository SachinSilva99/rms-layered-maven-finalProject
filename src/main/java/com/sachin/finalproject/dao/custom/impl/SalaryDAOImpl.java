package com.sachin.finalproject.dao.custom.impl;

import com.sachin.finalproject.dao.CrudDAO;
import com.sachin.finalproject.dao.custom.SalaryDAO;
import com.sachin.finalproject.dao.exception.ConstraintViolationException;
import com.sachin.finalproject.entity.Salary;
import com.sachin.finalproject.util.CrudUtil;

import java.lang.module.ResolutionException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SalaryDAOImpl implements SalaryDAO {
    private Connection connection;

    public SalaryDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Salary save(Salary salary) throws ConstraintViolationException {
        try {
            boolean isSaved = CrudUtil.execute("INSERT INTO salary(enic, ename, esalary, month, date, status) VALUES (?,?,?,?,?,?)",
                    salary.getEnic(),
                    salary.getName(),
                    salary.getSalary(),
                    salary.getMonth(),
                    salary.getDate(),
                    salary.getStatus()
            );
            if (!isSaved) throw new SQLException("Failed to save the salary");
            return salary;
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }

    }


    @Override
    public Salary update(Salary salary) throws ConstraintViolationException {
        boolean isUpdated = CrudUtil.execute("UPDATE salary SET enic = ?,ename = ?, esalary = ?, month = ?, date = ?, status = ?",
                salary.getEnic(),
                salary.getName(),
                salary.getSalary(),
                salary.getMonth(),
                salary.getDate(),
                salary.getStatus()
        );
        if(!isUpdated) try {
            throw new SQLException("Failed to update salary");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
        return salary;
    }

    @Override
    public void deleteByPk(Integer pk) throws ConstraintViolationException {
        boolean isDeleted = CrudUtil.execute("DELETE FROM  salary WHERE salaryId = ?", pk);
        if (!isDeleted) try {
            throw new SQLException("Failed to delete salary");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
    }

    @Override
    public List<Salary> findAll() {

        try {
            ResultSet rs = CrudUtil.execute("SELECT  * FROM salary");
            ArrayList<Salary> salaries = new ArrayList<>();
            return getSalaries(rs, salaries);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static ArrayList<Salary> getSalaries(ResultSet rs, ArrayList<Salary> salaries) throws SQLException {
        while (rs.next()) {
            String enic = rs.getString("enic");
            String ename = rs.getString("ename");
            double esalary = rs.getDouble("esalary");
            Date month = rs.getDate("month");
            Date date = rs.getDate("date");
            String status = rs.getString("status");
            Salary salary = new Salary(enic, ename, esalary, month.toLocalDate(), date.toLocalDate(), status);
            salaries.add(salary);
        }
        return salaries;
    }

    @Override
    public Optional<Salary> findByPk(Integer pk) {
        try {
            ResultSet rs = CrudUtil.execute("SELECT * FROM salary WHERE salaryId = ?", pk);
            ArrayList<Salary> salaries = getSalaries(rs, new ArrayList<>());
            if(salaries.isEmpty()){
                return Optional.empty();
            }
            return Optional.of(salaries.get(0));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existByPk(Integer pk) {
        ResultSet rs = CrudUtil.execute("SELECT * FROM salary WHERE salaryId = ?", pk);
        try {
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long count() {
        try {
            ResultSet rst = CrudUtil.execute("SELECT COUNT(salaryId) as salaryCount FROM salary");
            if (rst.next()) return rst.getLong("salaryCount");
        } catch (SQLException e) {
            throw new RuntimeException("failed to load salary count");
        }
        return 0;
    }

    @Override
    public List<Salary> getAllByDate(int month, int year) {
        try {
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
                s.setId(rst.getInt(7));
                salaries.add(s);
            }
            return salaries;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
