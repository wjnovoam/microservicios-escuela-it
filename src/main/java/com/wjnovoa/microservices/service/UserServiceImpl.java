package com.wjnovoa.microservices.service;

import com.wjnovoa.microservices.dao.repository.UserRepository;
import com.wjnovoa.microservices.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author William Johan Novoa Melendrez
 * @date 17/08/2022
 */
@Service
@Qualifier("BD")
@ConditionalOnProperty(prefix = "app", name = "edition", havingValue = "Community")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<UserDTO> getUserById(Long id) {
        Optional<UserDTO> userDTO = userRepository.findById(id);
        return userDTO;
    }

    @Override
    public List<UserDTO> listAllUsers(Pageable pageable) {
        Page<UserDTO> pageUsers = userRepository.findAll(pageable);
        List<UserDTO> users = pageUsers.getContent();
        //List<UserDTO> users = userRepository.findByEdadLessThan(25);
        //List<UserDTO> users = userRepository.findByNameLike("prueba");
        //List<UserDTO> users = userRepository.findByNameContaining("a");
        //List<UserDTO> users = userRepository.findAllUsersBetweenAgeAndName("william", 11, 40);
        return users;
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {

        UserDTO userSave = userRepository.save(userDTO);
        return userSave;
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}