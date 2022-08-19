package com.wjnovoa.microservices.dao.repository;

import com.wjnovoa.microservices.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author William Johan Novoa Melendrez
 * @date 18/08/2022
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    //Todos los usuarios menores a cierta edad
    List<UserEntity> findByAgeLessThan(int age);

    //Todos los usuarios mayores y igual a la edad
    List<UserEntity> findByAgeGreaterThanEqual(int age);

    //Buscar los usuario que tengan este name
    List<UserEntity> findByNameLike(String name);

    //Buscar todos los usuarios que contengan esta name en cualquier parte del nombre
    List<UserEntity> findByNameContaining(String name);

    @Query(value="select * from ms_users where name = ?1 and age >= ?2 and age <= ?3" ,nativeQuery = true)
    List<UserEntity> findAllUsersBetweenAgeAndName(String name,int ageBegin,int ageEnd);
}