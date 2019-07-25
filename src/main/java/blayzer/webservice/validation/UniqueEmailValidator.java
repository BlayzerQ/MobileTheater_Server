package blayzer.webservice.validation;

import blayzer.webservice.service.UserService;
import blayzer.webservice.validation.annotations.UniqueEmail;

import javax.annotation.Nullable;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserService userService;

    public UniqueEmailValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(@Nullable String email, ConstraintValidatorContext constraintValidatorContext) {
        return !userService.getByEmail(email).isPresent();
    }
}
