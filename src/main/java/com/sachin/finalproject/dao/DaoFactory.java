package com.sachin.finalproject.dao;

import com.sachin.finalproject.dao.custom.impl.*;

import java.sql.Connection;

public class DaoFactory {
    private static DaoFactory daoFactory;
    private DaoFactory(){}

    public static DaoFactory getInstance(){
        return daoFactory == null ? (daoFactory = new DaoFactory()) : daoFactory;
    }

    public <T extends SuperDAO> T getDao(Connection connection, DaoType daoType){
        switch (daoType) {
            case CUSTOMER:
                return (T) new CustomerDAOImpl(connection);

            case EMPLOYEE:
                return (T) new EmployeeDaoImpl(connection);

            case FOOD:
                return (T) new FoodDAOImpl(connection);

            case ITEM:
                return (T) new ItemDAOImpl(connection);

            case ORDER_DETAIL:
                return (T) new OrderDetailDAOImpl(connection);

            case ORDERS:
                return (T) new OrdersDAOImpl(connection);

            case SALARY:
                return (T) new SalaryDAOImpl(connection);

            case USER:
                return (T) new UserDAOImpl(connection);

            default:
                return null;
        }
    }
}
