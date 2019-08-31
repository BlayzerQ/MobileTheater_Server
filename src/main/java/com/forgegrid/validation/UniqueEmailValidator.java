package com.forgegrid.validation;

import com.forgegrid.bussines.service.impl.UserService;
import com.forgegrid.validation.annotations.UniqueEmail;
import lombok.RequiredArgsConstructor;

import javax.annotation.Nullable;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserService userService;

    @Override
    public boolean isValid(@Nullable String email, ConstraintValidatorContext constraintValidatorContext) {
        return !userService.isEmailRegistered(email);
    }
}
