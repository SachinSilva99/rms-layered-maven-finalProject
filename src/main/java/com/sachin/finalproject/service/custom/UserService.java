package com.sachin.finalproject.service.custom;

import com.sachin.finalproject.dto.UserDTO;
import com.sachin.finalproject.service.SuperService;
import com.sachin.finalproject.service.exception.DuplicateException;

import java.util.Optional;

public interface UserService extends SuperService {
    UserDTO saveUser(UserDTO userDTO)throws DuplicateException;
    Optional<UserDTO> getUser(String username);
}
