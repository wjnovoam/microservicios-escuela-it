package com.wjnovoa.microservices.configuration;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author William Johan Novoa Melendrez
 * @date 16/08/2022
 */
@Configuration
@Data
@ToString
@ConfigurationProperties(prefix = "app")
public class ApplicationConfig {

    private String name;

    private Integer year;

    private String edition;

    private String[] countries;
}