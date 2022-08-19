package com.wjnovoa.microservices.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author William Johan Novoa Melendrez
 * @date 16/08/2022
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class AccountEntity {
    private Long id;

    @NonNull
    private String name;
}