package com.sachin.finalproject.dao.custom.impl;

import com.sachin.finalproject.dao.CrudDAO;
import com.sachin.finalproject.dao.custom.ItemDAO;
import com.sachin.finalproject.dao.exception.ConstraintViolationException;
import com.sachin.finalproject.entity.Item;
import com.sachin.finalproject.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemDAOImpl implements ItemDAO {
    private Connection connection;

    public ItemDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Item save(Item item) throws ConstraintViolationException {
        boolean isSaved = CrudUtil.execute("INSERT INTO item (id, des, category, subcategory, qtyOnHand, price, size) VALUES (?, ?, ?, ?, ?, ?, ?)",
                item.getId(),
                item.getDes(),
                item.getCategory(),
                item.getSubCategory(),
                item.getQtyOnHand(),
                item.getPrice(),
                item.getSize()
        );
        if (!isSaved) try {
            throw new SQLException("Failed to save the Item");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
        return item;
    }

    @Override
    public Item update(Item item) throws ConstraintViolationException {
        try {
            boolean isUpdated = CrudUtil.execute("UPDATE item SET des=?, category=?, subcategory=?, qtyOnHand=?, price=?, size=? WHERE id=?",
                    item.getDes(),
                    item.getCategory(),
                    item.getQtyOnHand(),
                    item.getPrice(),
                    item.getSize(),
                    item.getId()
            );
            if (!isUpdated) throw new SQLException("Failed to update the item");
            return item;
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
    }

    @Override
    public void deleteByPk(String pk) throws ConstraintViolationException {
        try {
            boolean isDeleted = CrudUtil.execute("DELETE FROM item WHERE id = ?", pk);
            if (!isDeleted) throw new SQLException("Failed to delete the item");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
    }

    @Override
    public List<Item> findAll() {
        try {
            ResultSet rs = CrudUtil.execute("SELECT * FROM item");
            return getItems(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Optional<Item> findByPk(String pk) {
        try {
            ResultSet rs = CrudUtil.execute("SELECT * FROM item WHERE id = ?", pk);
            if (rs.next()) {
                String id = rs.getString("id");
                String des = rs.getString("des");
                String category = rs.getString("category");
                String subcategory = rs.getString("subcategory");
                int qtyOnHand = rs.getInt("qtyOnHand");
                double price = rs.getDouble("price");
                String size = rs.getString("size");

                Item item = new Item(0, id, qtyOnHand, des, price, category, subcategory, size);
                return Optional.of(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();


    }

    @Override
    public boolean existByPk(String pk) {
        ResultSet rs = CrudUtil.execute("SELECT * FROM item WHERE id = ?", pk);
        try {
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long count() {
        try {
            ResultSet rst = CrudUtil.execute("SELECT COUNT(id) as itemCount FROM item");
            if (rst.next()) return rst.getLong("itemCount");
        } catch (SQLException e) {
            throw new RuntimeException("failed to load item count");
        }
        return 0;
    }

    private ArrayList<Item> getItems(ResultSet rs) throws SQLException {
        ArrayList<Item> items = new ArrayList<>();
        while (rs.next()) {
            String id = rs.getString("id");
            String des = rs.getString("des");
            String category = rs.getString("category");
            String subcategory = rs.getString("subcategory");
            int qtyOnHand = rs.getInt("qtyOnHand");
            double price = rs.getDouble("price");
            String size = rs.getString("size");

            Item item = new Item(0, id, qtyOnHand, des, price, category, subcategory, size);
            items.add(item);
        }
        return items;
    }

    @Override
    public List<Item> getFoodCategoryAll() {
        ResultSet rst = CrudUtil.execute("SELECT * FROM item WHERE category = 'food'");
        try {
            return getItems(rst);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Item> getFoodSubcategoryCategory(String category,String subCategory) {
        ResultSet rs = CrudUtil.execute("SELECT * FROM item WHERE category = ? AND subcategory = ?", category, subCategory);
        try {
            return getItems(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Item> getFoodOnlyCategory(String category) {
        ResultSet rs = CrudUtil.execute("SELECT * FROM item WHERE category = ?", category);
        try {
            return getItems(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateQty(int newRemainingQty, String itemId) {
        String  sql = "UPDATE item SET `qtyOnHand` = ? WHERE `id` = ?";
        return CrudUtil.execute(sql, newRemainingQty, itemId);
    }
}
