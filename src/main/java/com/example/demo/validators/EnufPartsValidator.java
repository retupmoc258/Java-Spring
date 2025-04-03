package com.example.demo.validators;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 *
 *
 *
 */
public class EnufPartsValidator implements ConstraintValidator<ValidEnufParts, Product> {
    @Autowired
    private ApplicationContext context;
    public static  ApplicationContext myContext;
    @Override
    public void initialize(ValidEnufParts constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Product newProduct, ConstraintValidatorContext constraintValidatorContext) {
        if(context==null) return true;
        myContext=context;
        ProductService repo = myContext.getBean(ProductServiceImpl.class);
        if (newProduct.getId() != 0) {
            Product savedProduct = repo.findById((int) newProduct.getId());
            for (Part p : savedProduct.getParts()) {
                if ( p.getInv() < (newProduct.getInv() - savedProduct.getInv()) )return false;
            }
        }
        return true;
    }
}
