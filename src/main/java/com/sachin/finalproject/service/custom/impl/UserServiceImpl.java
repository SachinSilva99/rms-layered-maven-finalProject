package com.sachin.finalproject.service.custom.impl;

import com.sachin.finalproject.dao.DaoFactory;
import com.sachin.finalproject.dao.DaoType;
import com.sachin.finalproject.dao.custom.UserDAO;
import com.sachin.finalproject.db.DBConnection;
import com.sachin.finalproject.dto.UserDTO;
import com.sachin.finalproject.entity.User;
import com.sachin.finalproject.service.custom.UserService;
import com.sachin.finalproject.service.exception.DuplicateException;
import com.sachin.finalproject.service.util.Converter;

import java.sql.Connection;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final Connection connection = DBConnection.getInstance().getConnection();
    private final UserDAO userDAO = DaoFactory.getInstance().getDao(connection, DaoType.USER);
    private final Converter converter = new Converter();
    @Override
    public UserDTO saveUser(UserDTO userDTO) throws DuplicateException {
        if(userDAO.existByPk(userDTO.getUsername()))throw new DuplicateException("User already exists");
        userDAO.save(converter.toUser(userDTO));
        return userDTO;
    }

    @Override
    public Optional<UserDTO> getUser(String username) {
        Optional<User> userOptional = userDAO.findByPk(username);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            return Optional.of(converter.fromUser(user));
        }
        return Optional.empty();
    }
}
