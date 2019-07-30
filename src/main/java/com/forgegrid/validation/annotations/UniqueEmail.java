package com.forgegrid.validation.annotations;

import com.forgegrid.validation.UniqueEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Email;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueEmailValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
@Email(message = "Email should be valid!")
public @interface UniqueEmail {
    String message() default "There is already user with this email!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
