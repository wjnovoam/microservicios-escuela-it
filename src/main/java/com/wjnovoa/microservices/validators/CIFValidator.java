package com.wjnovoa.microservices.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author William Johan Novoa Melendrez
 * @date 17/08/2022
 */
public class CIFValidator implements ConstraintValidator<CIF, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return false;
        }
        return value.length() == 9;

    }
}