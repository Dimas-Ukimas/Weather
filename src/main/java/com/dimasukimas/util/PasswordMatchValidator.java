package com.dimasukimas.util;

import com.dimasukimas.dto.request.UserRegistrationDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, UserRegistrationDto> {


    @Override
    public boolean isValid(UserRegistrationDto dto, ConstraintValidatorContext context) {
        if (dto.password() == null || dto.repeatPassword() == null) {
            return false;
        }

        if (!dto.password().equals(dto.repeatPassword())) {
            context.buildConstraintViolationWithTemplate("Passwords do not match")
                    .addPropertyNode("repeatPassword")
                    .addConstraintViolation();

            return false;
        }

        return true;
    }
}
