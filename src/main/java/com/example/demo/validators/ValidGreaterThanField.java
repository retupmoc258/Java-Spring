package com.example.demo.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {GreaterThanValidator.class})
@Repeatable(ValidGreaterThanFields.class)
public @interface ValidGreaterThanField {
    String message() default "Field value must be more than the dependent value";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String greaterFieldName();
    String dependentFieldName();
    boolean strictlyGreater() default true;
}



