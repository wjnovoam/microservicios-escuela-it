package com.wjnovoa.microservices.service;

import com.wjnovoa.microservices.dao.entity.UserEntity;
import com.wjnovoa.microservices.dao.repository.UserRepository;
import com.wjnovoa.microservices.mappers.UserMapper;
import com.wjnovoa.microservices.model.UserDTO;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author William Johan Novoa Melendrez
 * @date 17/08/2022
 */
@Service
@Qualifier("BD")
@ConditionalOnProperty(prefix = "app", name = "edition", havingValue = "Community")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<UserDTO> getUserById(Long id) {

        /*UserDTO userDTO = userRepository.findById(id)
                .map(entidad -> userMapper.getUserDTO(entidad))
                .orElseThrow();*/

        Optional<UserEntity> optUserDto = userRepository.findById(id);
        UserEntity userEntity = optUserDto.get();
        UserDTO userDTO = userMapper.getUserDTO(userEntity);
        return Optional.of(userDTO);
    }

    @Override
    public List<UserDTO> listAllUsers(Pageable pageable) {
        /*List<UserDTO> pageUser = userRepository.findAll(pageable)
                .stream()
                .map(userEntity -> userMapper.getUserDTO(userEntity))
                .collect(Collectors.toList());*/

        Page<UserEntity> pageUsers = userRepository.findAll(pageable);
        List<UserEntity> users = pageUsers.getContent();

        List<UserDTO> userDTOS = userMapper.getUserDTOs(users);

        return userDTOS;
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {

       /*UserDTO userDTO1 = Stream.of(userDTO)
               .map(user -> userMapper.getUserEntity(user))
               .map(userEntity -> userRepository.save(userEntity))
               .map(userEntity -> userMapper.getUserDTO(userEntity))
               .findAny().get();*/

        UserEntity userEntity = userMapper.getUserEntity(userDTO);
        UserEntity userSave = userRepository.save(userEntity);
        return userMapper.getUserDTO(userSave);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}