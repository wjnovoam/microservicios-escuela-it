package com.wjnovoa.microservices.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author William Johan Novoa Melendrez
 * @date 16/08/2022
 */
@Data
@NoArgsConstructor
public class CountryEntity {

    private String isoCode;

    private  String name;

    private String flag;
}