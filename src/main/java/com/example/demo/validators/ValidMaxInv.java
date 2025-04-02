package com.example.demo.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {MaxInvValidator.class})
public @interface ValidMaxInv {
    String message() default "Field value must be more than the dependent value";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String maxFieldName();
    String dependentFieldName();
}
