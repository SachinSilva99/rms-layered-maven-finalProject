package com.sachin.finalproject.service.custom;

import com.sachin.finalproject.dao.custom.ItemDAO;
import com.sachin.finalproject.dto.FoodDTO;
import com.sachin.finalproject.dto.ItemDTO;
import com.sachin.finalproject.service.SuperService;
import com.sachin.finalproject.service.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public interface ItemService extends SuperService {
    ItemDTO getItem(String id)throws NotFoundException;

    List<ItemDTO> getAllItem(String category);
    List<ItemDTO> getAllItem(String category, String subCategory);
    boolean saveItem(ItemDTO itemDTO);
    List<ItemDTO> searchItem(String text,String category);

    boolean deleteItem(String itemId);
    boolean updateQty(int newRemainingQty, String itemId);

    boolean addNewFood(String category, String subCategory, String name);

}
