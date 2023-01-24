package com.sachin.finalproject.service.custom;

import com.sachin.finalproject.dto.FoodDTO;
import com.sachin.finalproject.dto.ItemDTO;
import com.sachin.finalproject.service.SuperService;
import com.sachin.finalproject.service.exception.DuplicateException;
import com.sachin.finalproject.service.exception.NotFoundException;
import com.sachin.finalproject.to.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface StockService extends SuperService {
    ArrayList<String> getAllFoodNames(String category, String subCategory);
    Optional<ItemDTO> getItem(String id);
    Optional<String> getFoodName(String id);
    ItemDTO saveItem(ItemDTO itemDTO)throws DuplicateException,NotFoundException;
    ItemDTO updateItem(ItemDTO itemDTO)throws NotFoundException;
    List<ItemDTO> getAllItems();
}
