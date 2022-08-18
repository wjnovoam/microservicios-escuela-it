package com.wjnovoa.microservices.service;

import com.wjnovoa.microservices.model.UserDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author William Johan Novoa Melendrez
 * @date 17/08/2022
 */
@Service
@Qualifier("BD")
@ConditionalOnProperty(prefix = "app", name = "edition", havingValue = "Community")
public class UserServiceImpl implements UserService{

    @Override
    public Optional<UserDTO> getUserById(Long id) {
        UserDTO userDTO = new UserDTO(1L, "William");

        return Optional.of(userDTO);
    }
}