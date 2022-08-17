package com.wjnovoa.microservices.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author William Johan Novoa Melendrez
 * @date 17/08/2022
 */
@Documented
@Constraint(validatedBy = CIFValidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CIF {
    String message() default "Invalid CIF Number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}