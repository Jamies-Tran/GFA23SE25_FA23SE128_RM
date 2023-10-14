package com.realman.becore.custom_constrain.address;

import java.util.Objects;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AddressValidator implements ConstraintValidator<Address, String> {

    @Override
    public void initialize(Address constraintAnnotation) {

        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return Objects.nonNull(value) && !value.isEmpty();
    }

}
