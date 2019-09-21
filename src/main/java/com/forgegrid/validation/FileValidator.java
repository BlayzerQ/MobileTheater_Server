package com.forgegrid.validation;

import com.forgegrid.validation.annotations.ValidFile;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FileValidator implements ConstraintValidator<ValidFile, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        if (multipartFile.isEmpty()) {
            return false;
        }
        return FileNameValidator.isFileNameValid(multipartFile.getOriginalFilename());
    }
}
