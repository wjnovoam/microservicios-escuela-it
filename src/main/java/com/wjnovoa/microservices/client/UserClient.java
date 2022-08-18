package com.wjnovoa.microservices.client;

import com.wjnovoa.microservices.model.UserDTO;

/**
 * @author William Johan Novoa Melendrez
 * @date 17/08/2022
 */
public interface UserClient {
    UserDTO getUser(Long id);
}