package com.disney.studios.Validators;

import lombok.extern.slf4j.Slf4j;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class BlankValidator implements ConstraintValidator<BlankValidation, String> {
    @Override
    public void initialize(BlankValidation constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s.trim().isEmpty())
        {
            return false;
        }
        return true;
    }
}
