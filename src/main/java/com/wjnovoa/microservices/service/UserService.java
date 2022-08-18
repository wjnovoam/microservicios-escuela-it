package com.wjnovoa.microservices.service;

import com.wjnovoa.microservices.model.UserDTO;

import java.util.List;
import java.util.Optional;

/**
 * @author William Johan Novoa Melendrez
 * @date 17/08/2022
 */
public interface UserService {
    Optional<UserDTO> getUserById(Long id);

    List<UserDTO> listAllUsers();

    UserDTO saveUser(UserDTO userDTO);

    void deleteById(Long id);

}