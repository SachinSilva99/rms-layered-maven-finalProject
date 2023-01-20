package com.sachin.finalproject.dao.custom.impl;

import com.sachin.finalproject.dao.custom.FoodDAO;
import com.sachin.finalproject.dao.exception.ConstraintViolationException;
import com.sachin.finalproject.entity.Food;
import com.sachin.finalproject.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FoodDAOImpl implements FoodDAO {
    private Connection connection;

    public FoodDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Food save(Food food) throws ConstraintViolationException {
        try {
            boolean isSaved = CrudUtil.execute("INSERT INTO food VALUES (?,?,?)",
                    food.getName(), food.getCategory(), food.getSubCategory());
            if (!isSaved) {
                throw new SQLException("Food not saved");
            }
            return food;

        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }

    }

    @Override
    public Food update(Food food) throws ConstraintViolationException {
        try {
            boolean isUpdated = CrudUtil.execute("UPDATE food SET category = ?, subcategory = ? WHERE name = ?",
                    food.getCategory(), food.getSubCategory(), food.getName());
            if (!isUpdated) {
                throw new SQLException("Failed to update");
            }
            return food;
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }

    }

    @Override
    public void deleteByPk(String pk) throws ConstraintViolationException {
        try {
            boolean isDeleted = CrudUtil.execute("DELETE from food WHERE name = ?", pk);
            if (!isDeleted) {
                throw new SQLException("Failed to delete");
            }
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
    }

    @Override
    public List<Food> findAll() {
        ArrayList<Food> foods = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM food");
        return getFoods(foods, rst);
    }


    @Override
    public Optional<Food> findByPk(String pk) {
        ResultSet rst = CrudUtil.execute("SELECT * FROM food WHERE name = ?", pk);
        try {
            if (rst.next()) {
                Food food = new Food(
                        rst.getString("name"),
                        rst.getString("category"),
                        rst.getString("subcategory")
                );
                return Optional.of(food);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public boolean existByPk(String pk) {
        ResultSet rst = CrudUtil.execute("SELECT * FROM food WHERE name = ?", pk);
        try {
            return rst.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long count() {
        try {
            ResultSet rst = CrudUtil.execute("SELECT COUNT(name) as foodCount FROM food");
            if (rst.next()) return rst.getLong("foodCount");
        } catch (SQLException e) {
            throw new RuntimeException("failed to load food count");
        }
        return 0;
    }

    private ArrayList<Food> getFoods(ArrayList<Food> foods, ResultSet rst) {
        try {
            while (rst.next()) {
                Food food = new Food(
                        rst.getString("name"),
                        rst.getString("category"),
                        rst.getString("subcategory")
                );
                foods.add(food);
            }
            return foods;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
