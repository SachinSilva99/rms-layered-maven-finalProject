package com.sachin.finalproject.service.custom.impl;

import com.sachin.finalproject.dao.DaoFactory;
import com.sachin.finalproject.dao.DaoType;
import com.sachin.finalproject.dao.custom.FoodDAO;
import com.sachin.finalproject.dao.custom.ItemDAO;
import com.sachin.finalproject.db.DBConnection;
import com.sachin.finalproject.dto.ItemDTO;
import com.sachin.finalproject.entity.Food;
import com.sachin.finalproject.entity.Item;
import com.sachin.finalproject.service.custom.ItemService;
import com.sachin.finalproject.service.exception.DuplicateException;
import com.sachin.finalproject.service.exception.InUseException;
import com.sachin.finalproject.service.exception.NotFoundException;
import com.sachin.finalproject.service.util.Converter;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ItemServiceImpl implements ItemService {
    private final Connection connection;
    private final ItemDAO itemDAO;
    private final FoodDAO foodDAO;
    private final Converter converter;

    public ItemServiceImpl() {
        this.connection = DBConnection.getInstance().getConnection();
        this.itemDAO = DaoFactory.getInstance().getDao(connection, DaoType.ITEM);
        this.foodDAO = DaoFactory.getInstance().getDao(connection,DaoType.FOOD);
        this.converter = new Converter();
    }

    @Override
    public ItemDTO getItem(String id) throws NotFoundException {
        Optional<Item> item = itemDAO.findByPk(id);
        if(item.isPresent()) {
           return converter.fromItem(item.get());
        }
        throw new NotFoundException("Item not found");
    }

    @Override
    public List<ItemDTO> getAllItem(String category) {
        return itemDAO.getFoodOnlyCategory(category).stream().map(item -> converter.fromItem(item)).collect(Collectors.toList());
    }

    @Override
    public List<ItemDTO> getAllItem(String category, String subCategory) {
        return itemDAO.getFoodSubcategoryCategory(category,subCategory).stream().map(item -> converter.fromItem(item)).collect(Collectors.toList());
    }



    @Override
    public boolean saveItem(ItemDTO itemDTO) {
        if(itemDAO.existByPk(itemDTO.getId()))throw new DuplicateException("Itme exists");
        itemDAO.save(converter.toItem(itemDTO));
        return true;
    }

    @Override
    public List<ItemDTO> searchItem(String text, String category) {
        List<Item> foodOnlyCategory = itemDAO.getFoodOnlyCategory(category);
        List<ItemDTO> itemDtoList = foodOnlyCategory.stream().map(item -> converter.fromItem(item)).collect(Collectors.toList());
        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();
        for (ItemDTO itemDTO : itemDtoList) {
            if(itemDTO.getDes().contains(text) || itemDTO.getId().contains(text)){
                itemDTOS.add(itemDTO);
            }
        }
        return itemDTOS;
    }

    @Override
    public boolean deleteItem(String itemId) {
        if(!itemDAO.existByPk(itemId))throw new NotFoundException();
        itemDAO.deleteByPk(itemId);
        return true;
    }

    @Override
    public boolean updateQty(int newRemainingQty, String itemId) {
        if(!itemDAO.existByPk(itemId))throw new NotFoundException();
        return itemDAO.updateQty(newRemainingQty, itemId);
    }

    @Override
    public boolean addNewFood(String category, String subCategory, String name) {
        if(foodDAO.existByPk(name))throw new InUseException("Food name in use");
        Food save = foodDAO.save(new Food(category, subCategory, name));
        return save != null;
    }
}
