package com.sachin.finalproject.service;

import com.sachin.finalproject.service.custom.impl.*;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return serviceFactory == null ? (serviceFactory = new ServiceFactory()) : serviceFactory;
    }

    public <T extends SuperService> T getService(ServiceType serviceType) {
        switch (serviceType) {
            case CUSTOMER:
                return (T) new CustomerServiceImpl();

            case EMPLOYEE:
                return (T) new EmployeeServiceImpl();

            case ITEM:
                return (T) new ItemServiceImpl();

            case ORDER:
                return (T) new OrderServiceImpl();

            case SALARY:
                return (T) new SalaryServiceImpl();

            case STOCK:
                return (T) new StockServiceImpl();

            case USER:
                return (T) new UserServiceImpl();

            default:
                return null;
        }

    }
}
