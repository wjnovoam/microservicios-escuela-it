package com.wjnovoa.microservices.dao.repository;

import com.wjnovoa.microservices.dao.entity.UserEntity;
import com.wjnovoa.microservices.model.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author William Johan Novoa Melendrez
 * @date 18/08/2022
 */
@Repository
public interface UserRepository extends JpaRepository<UserDTO, Long> {

}