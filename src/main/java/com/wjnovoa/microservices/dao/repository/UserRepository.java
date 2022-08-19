package com.wjnovoa.microservices.dao.repository;

import com.wjnovoa.microservices.dao.entity.UserEntity;
import com.wjnovoa.microservices.model.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author William Johan Novoa Melendrez
 * @date 18/08/2022
 */
@Repository
public interface UserRepository extends JpaRepository<UserDTO, Long> {

    //Todos los usuarios menores a cierta edad
    List<UserDTO> findByEdadLessThan(int edad);

    //Todos los usuarios mayores y igual a la edad
    List<UserDTO> findByEdadGreaterThanEqual(int edad);

    //Buscar los usuario que tengan este name
    List<UserDTO> findByNameLike(String name);

    //Buscar todos los uduaro que contengan esta name en cualquier parte del nombre
    List<UserDTO> findByNameContaining(String name);

    @Query(value="select * from ms_users where name = ?1 and edad >= ?2 and edad <= ?3" ,nativeQuery = true)
    List<UserDTO> findAllUsersBetweenAgeAndName(String name,int ageBegin,int ageEnd);
}