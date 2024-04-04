package com.realman.becore.custom_constrain.text;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NormalTextValidator implements ConstraintValidator<NormalText, String> {


    @Override
    public void initialize(NormalText constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.length() <= 255;
    }
    
}
