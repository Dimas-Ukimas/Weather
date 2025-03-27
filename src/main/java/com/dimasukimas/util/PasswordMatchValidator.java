package com.dimasukimas.util;

import com.dimasukimas.dto.UserRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, UserRequestDto> {


    @Override
    public boolean isValid(UserRequestDto dto, ConstraintValidatorContext context) {
        if (dto.password() == null || dto.repeatPassword() == null) {
            return false;
        }

        return dto.password().equals(dto.repeatPassword());
    }
}
