package com.wjnovoa.microservices.mappers;

import com.wjnovoa.microservices.dao.entity.UserEntity;
import com.wjnovoa.microservices.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author William Johan Novoa Melendrez
 * @date 19/08/2022
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "userDTO.edad",target = "age")
    UserEntity getUserEntity(UserDTO userDTO);

    @Mapping(source = "userEntity.age",target = "edad")
    UserDTO getUserDTO(UserEntity userEntity);

    List<UserDTO> getUserDTOs(List<UserEntity> userEntities);
}