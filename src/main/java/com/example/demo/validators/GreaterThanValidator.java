package com.example.demo.validators;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GreaterThanValidator implements ConstraintValidator<ValidGreaterThanField, Object> {
    private String greaterFieldName;
    private String dependentFieldName;
    private boolean strictlyGreater = true;

    @Override
    public void initialize(ValidGreaterThanField constraint) {
        this.greaterFieldName = constraint.greaterFieldName();
        this.dependentFieldName = constraint.dependentFieldName();
        this.strictlyGreater = constraint.strictlyGreater();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            Object maxFieldValue = new BeanWrapperImpl(value).getPropertyValue(greaterFieldName);
            Object dependentFieldValue = new BeanWrapperImpl(value).getPropertyValue(dependentFieldName);

            //Can't compare null fields.
            if (maxFieldValue == null || dependentFieldValue == null) { return true;}

            if (maxFieldValue instanceof Number && dependentFieldValue instanceof Number) {
                if (strictlyGreater) {
                    return ((Number) maxFieldValue).intValue() > ((Number) dependentFieldValue).intValue();
                } else {
                    return ((Number) maxFieldValue).intValue() >= ((Number) dependentFieldValue).intValue();
                }
            } else {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                        .addPropertyNode("greaterThanFor{" + value.getClass().getSimpleName() + "}." + greaterFieldName).addConstraintViolation();
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
