package com.forgegrid.validation;

import com.forgegrid.validation.annotations.ValidFile;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FileValidator implements ConstraintValidator<ValidFile, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
        if (multipartFile.isEmpty()) {
            return false;
        }
        String originalFileName = multipartFile.getOriginalFilename();
        if (originalFileName == null) {
            return false;
        }
        String fileName = StringUtils.cleanPath(originalFileName);
        return !fileName.contains("..");
    }
}
