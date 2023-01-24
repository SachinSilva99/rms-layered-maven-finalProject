package com.sachin.finalproject.dao.custom.impl;

import com.sachin.finalproject.dao.CrudDAO;
import com.sachin.finalproject.dao.custom.UserDAO;
import com.sachin.finalproject.dao.exception.ConstraintViolationException;
import com.sachin.finalproject.entity.User;
import com.sachin.finalproject.util.CrudUtil;
import javafx.scene.image.Image;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {
    private Connection connection;

    public UserDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User save(User user) throws ConstraintViolationException {
        boolean isSaved = CrudUtil.execute("INSERT INTO user(username, password, image, role, usertype, name) VALUES (?,?,?,?,?,?)",
                user.getUsername(),
                user.getPassword(),
                user.getImg(),
                user.getRole(),
                user.getUserType(),
                user.getName()
        );
        if (!isSaved) try {
            throw new SQLException("Failed to save user");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
        return user;
    }

    @Override
    public User update(User user) throws ConstraintViolationException {
        boolean isUpdated = CrudUtil.execute("UPDATE user SET password = ?, image = ?, role = ?,usertype = ?, name = ? WHERE username = ?",
                user.getPassword(),
                user.getImg(),
                user.getRole(),
                user.getUserType(),
                user.getName(),
                user.getUsername()
        );
        if (!isUpdated) try {
            throw new SQLException("Failed to update user");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
        return user;
    }

    @Override
    public void deleteByPk(String pk) throws ConstraintViolationException {
        boolean isDeleted = CrudUtil.execute("DELETE FROM user WHERE username = ?", pk);
        if(!isDeleted) try {
            throw new SQLException("Failed to delete the user");
        } catch (SQLException e) {
            throw new ConstraintViolationException(e);
        }
    }

    @Override
    public List<User> findAll() {
        try {
            ResultSet rs = CrudUtil.execute("SELECT * FROM user");
            return getUsers(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static List<User> getUsers(ResultSet rs) throws SQLException {
        List<User> users = new ArrayList<User>();
        while (rs.next()) {
            String username = rs.getString("username");
            String password = rs.getString("password");
            byte[] image = rs.getBytes("image");
            String role = rs.getString("role");
            String usertype = rs.getString("usertype");
            String name = rs.getString("name");
            Image image1 = null;
            User user = new User(username,password,role,usertype,image1,name);
            users.add(user);
        }
        return users;
    }

    @Override
    public Optional<User> findByPk(String pk) {
        try {
            ResultSet rs = CrudUtil.execute("SELECT * FROM user WHERE username = ?", pk);
            List<User> users = getUsers(rs);
            if(users.isEmpty()) return Optional.empty();
            return Optional.of(users.get(0));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existByPk(String pk) {
        ResultSet rs = CrudUtil.execute("SELECT * FROM user WHERE username = ?", pk);
        try {
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long count() {
        try {
            ResultSet rst = CrudUtil.execute("SELECT COUNT(username) as usernameCount FROM user");
            if (rst.next()) return rst.getLong("usernameCount");
        } catch (SQLException e) {
            throw new RuntimeException("failed to load username count");
        }
        return 0;
    }
}
