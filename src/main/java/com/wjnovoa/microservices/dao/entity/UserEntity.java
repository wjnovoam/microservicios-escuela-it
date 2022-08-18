package com.wjnovoa.microservices.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

/**
 * @author William Johan Novoa Melendrez
 * @date 18/08/2022
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor
//@Entity(name = "ms_users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NonNull
    private Long id;

    @NonNull
    private String name;

    @Column(name = "last_name")
    private String lastname;

    private int edad;

}