package com.sachin.finalproject.dao;

import com.sachin.finalproject.dao.custom.impl.CustomerDAOImpl;

import java.sql.Connection;

public class DaoFactory {
    private static DaoFactory daoFactory;
    private DaoFactory(){}

    public static DaoFactory getInstance(){
        return daoFactory == null ? (daoFactory = new DaoFactory()) : daoFactory;
    }

   /* public <T extends SuperDAO> T getDao(Connection connection, DaoType daoType){
        switch (daoType) {
            case CUSTOMER:
                return (T) new CustomerDAOImpl(connection);
        }
    }*/
}
