package com.wjnovoa.microservices.service;

import com.wjnovoa.microservices.client.UserClient;
import com.wjnovoa.microservices.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author William Johan Novoa Melendrez
 * @date 17/08/2022
 */
@Primary
@Service
@Qualifier("CLOUD")
@ConditionalOnProperty(prefix = "app", name = "edition", havingValue = "Pro")
public class UserServiceCloudImpl implements UserService{

    @Autowired
    private UserClient userClient;

    @Override
    public Optional<UserDTO> getUserById(Long id) {

        UserDTO userDTO = userClient.getUser(id);
        return Optional.ofNullable(userDTO);
    }
}