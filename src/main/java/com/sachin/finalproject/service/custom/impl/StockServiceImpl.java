package com.sachin.finalproject.service.custom.impl;

import com.sachin.finalproject.dao.DaoFactory;
import com.sachin.finalproject.dao.DaoType;
import com.sachin.finalproject.dao.custom.FoodDAO;
import com.sachin.finalproject.dao.custom.ItemDAO;
import com.sachin.finalproject.db.DBConnection;
import com.sachin.finalproject.dto.ItemDTO;
import com.sachin.finalproject.entity.Food;
import com.sachin.finalproject.entity.Item;
import com.sachin.finalproject.service.custom.StockService;
import com.sachin.finalproject.service.exception.DuplicateException;
import com.sachin.finalproject.service.exception.NotFoundException;
import com.sachin.finalproject.service.util.Converter;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StockServiceImpl implements StockService {
    private final FoodDAO foodDAO ;
    private final ItemDAO itemDAO ;
    private final Connection connection;
    private final Converter converter;

    public StockServiceImpl() {
        this.connection = DBConnection.getInstance().getConnection();
        this.foodDAO = DaoFactory.getInstance().getDao(connection, DaoType.FOOD);
        this.itemDAO = DaoFactory.getInstance().getDao(connection,DaoType.ITEM);
        this.converter = new Converter();
    }

    @Override
    public ArrayList<String> getAllFoodNames(String category, String subCategory) {
        List<Food> foods = foodDAO.findAll();
        return (ArrayList<String>) foods.stream().map(food -> food.getName()).collect(Collectors.toList());
    }

    @Override
    public Optional<ItemDTO> getItem(String id) {
        Optional<Item> item = itemDAO.findByPk(id);
        if(item.isPresent()){
            ItemDTO itemDTO = converter.fromItem(item.get());
            return Optional.of(itemDTO);
        }
        return Optional.empty();

    }

    @Override
    public Optional<String> getFoodName(String id) {
        Optional<Food> name = foodDAO.findByPk(id);
        if(name.isPresent()){
            return Optional.of(name.get().getName());
        }
        return Optional.empty();
    }

    @Override
    public ItemDTO saveItem(ItemDTO itemDTO) throws DuplicateException, NotFoundException {
        if(itemDAO.existByPk(itemDTO.getId()))throw new DuplicateException("Already exists");
        itemDAO.save(converter.toItem(itemDTO));
        return itemDTO;
    }

    @Override
    public ItemDTO updateItem(ItemDTO itemDTO) throws NotFoundException {
        if(!itemDAO.existByPk(itemDTO.getId()))throw new NotFoundException("Item not found");
        itemDAO.update(converter.toItem(itemDTO));
        return itemDTO;
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return itemDAO.findAll().stream().map(item -> converter.fromItem(item)).collect(Collectors.toList());
    }
}
