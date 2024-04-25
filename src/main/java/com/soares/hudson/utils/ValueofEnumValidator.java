package com.soares.hudson.utils;

import java.util.List;
import java.util.stream.Stream;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValueofEnumValidator implements ConstraintValidator<ValueOfEnum, CharSequence> {

    private List<String> valoresAceitos;

    @Override
    public void initialize(ValueOfEnum constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        valoresAceitos = Stream.of(constraintAnnotation.enumClass().getEnumConstants()).map(Enum::name).toList();
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if( value == null ){
            return true;
        }
        return valoresAceitos.contains(value);
    }
    
}
