package com.example.demo.validators;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MaxInvValidator implements ConstraintValidator<ValidMaxInv, Object> {
    private String maxFieldName;
    private String dependentFieldName;

    @Override
    public void initialize(ValidMaxInv constraint) {
        this.maxFieldName = constraint.maxFieldName();
        this.dependentFieldName = constraint.dependentFieldName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            Object maxFieldValue = new BeanWrapperImpl(value).getPropertyValue(maxFieldName);
            Object dependentFieldValue = new BeanWrapperImpl(value).getPropertyValue(dependentFieldName);

            //Can't compare null fields.
            if (maxFieldValue == null || dependentFieldValue == null) { return true;}

            if (maxFieldValue instanceof Number && dependentFieldValue instanceof Number) {
                return ((Number) maxFieldValue).intValue() > ((Number) dependentFieldValue).intValue();
            } else {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate()).addPropertyNode("maxInv").addConstraintViolation();
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
