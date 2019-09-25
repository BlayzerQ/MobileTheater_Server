package com.forgegrid.validation.annotations;

import com.forgegrid.validation.FileValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FileValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER })
public @interface ValidFile {
    String message() default "Invalid file!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
