package com.forgegrid.validation.annotations;

import com.forgegrid.validation.FileNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FileNameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ValidFileName {
    String message() default "Invalid file name!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
