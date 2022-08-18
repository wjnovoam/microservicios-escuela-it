package com.wjnovoa.microservices.client;

import com.wjnovoa.microservices.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author William Johan Novoa Melendrez
 * @date 17/08/2022
 */
@Service
public class UserClientImpl implements UserClient{

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public UserDTO getUser(Long id) {
        String fooResourceUrl = "https://jsonplaceholder.typicode.com/posts/"+id;
        return restTemplate.getForObject(fooResourceUrl, UserDTO.class);
    }
}