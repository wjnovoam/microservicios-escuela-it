package com.wjnovoa.microservices.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * @author William Johan Novoa Melendrez
 * @date 17/08/2022
 */
@Data
@AllArgsConstructor
public class ApiError {

    private HttpStatus status;
    private String message;
    private List<String> errors;
}