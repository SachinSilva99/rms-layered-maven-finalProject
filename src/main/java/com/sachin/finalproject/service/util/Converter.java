package com.sachin.finalproject.service.util;

import com.sachin.finalproject.dto.*;
import com.sachin.finalproject.entity.*;


public class Converter {
    public Customer toCustomer(CustomerDTO cd){
        return new Customer(
                cd.getName(),cd.getGender(),cd.getAddress(), cd.getAddress(),cd.getPhoneNumber()
        );
    }
    public CustomerDTO fromCustomer(Customer c){
        return new CustomerDTO(
                c.getName(),c.getGender(),c.getAddress(), c.getAddress(),c.getPhoneNumber()
        );
    }
    public Employee toEmployee(EmployeeDTO ed){
        return new Employee(
                ed.getPhoneNumber(),ed.getNic(),ed.getName(),ed.getRole(),ed.getSalary(),ed.getDob(),ed.getAddress(),ed.getGender()
        );
    }
    public EmployeeDTO fromEmployee(Employee e){
        return new EmployeeDTO(
                e.getPhoneNumber(),e.getNic(),e.getName(),e.getRole(),e.getSalary(),e.getDob(),e.getAddress(),e.getGender()
        );
    }
    public Food toFood(FoodDTO foodDTO){
        return new Food(foodDTO.getName(),foodDTO.getCategory(),foodDTO.getCategory());
    }
    public FoodDTO fromFood(Food food){
        return new FoodDTO(food.getName(),food.getCategory(),food.getCategory());
    }
    public Item toItem(ItemDTO itemDTO){
        return new Item(
                itemDTO.getQty(),
                itemDTO.getId(),
                itemDTO.getQtyOnHand(),
                itemDTO.getDes(),
                itemDTO.getPrice(),
                itemDTO.getCategory(),
                itemDTO.getSubCategory(),
                itemDTO.getSize()
        );
    }
    public ItemDTO fromItem(Item item){
        return new ItemDTO(
                item.getQty(),
                item.getId(),
                item.getQtyOnHand(),
                item.getDes(),
                item.getPrice(),
                item.getCategory(),
                item.getSubCategory(),
                item.getSize()
        );
    }
    public OrderDetail toOrderDetail(OrderDetailDTO od){
        return new OrderDetail(od.getOrderId(),od.getItemID(),od.getQty(),od.getUnitPrice());
    }
    public OrderDetailDTO fromOrderDetail(OrderDetail o){
        return new OrderDetailDTO(o.getOrderId(),o.getItemID(),o.getQty(),o.getUnitPrice());
    }
    public Orders toOrders(OrdersDTO od){
        return new Orders(od.getId(),od.getDate(),od.getCustomerId(),od.getOrderType());
    }
    public OrdersDTO fromOrders(Orders od){
        return new OrdersDTO(od.getId(),od.getDate(),od.getCustomerId(),od.getOrderType());
    }
    public Salary toSalary(SalaryDTO sd){
        return new Salary(sd.getEnic(),sd.getName(),sd.getSalary(),sd.getMonth(),sd.getDate(),sd.getStatus());
    }
    public SalaryDTO fromSalary(Salary sd){
        return new SalaryDTO(sd.getEnic(),sd.getName(),sd.getSalary(),sd.getMonth(),sd.getDate(),sd.getStatus());
    }
    public User toUser(UserDTO userDTO){
        return new User(userDTO.getUsername(),userDTO.getPassword(),userDTO.getRole(),userDTO.getUserType(),userDTO.getImg(),userDTO.getName());
    }
    public UserDTO fromUser(User user){
        return new UserDTO(user.getUsername(),user.getPassword(),user.getRole(),user.getUserType(),user.getImg(),user.getName());
    }
}
