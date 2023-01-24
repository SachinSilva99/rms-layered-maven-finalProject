package com.sachin.finalproject.service.custom;

import com.sachin.finalproject.dto.OrderDetailDTO;
import com.sachin.finalproject.dto.OrdersDTO;
import com.sachin.finalproject.service.SuperService;

import java.util.ArrayList;
import java.util.Optional;

public interface OrderService extends SuperService {
    String generateId(String  id);
    Optional<String> getOrderId();

    boolean placeOrder(OrdersDTO ordersDTO, ArrayList<OrderDetailDTO> orderDetailDTOS);
}
