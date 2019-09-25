package com.forgegrid.validation;

import com.forgegrid.validation.annotations.ValidFileName;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FileNameValidator implements ConstraintValidator<ValidFileName, String> {

    private static final String[] ILLEGAL_SYMBOLS = {"/", "\n", "\r", "\t", "\0", "\f", "`", "?", "*", "\\", "<", ">", "|", "\"", ":", ".."};

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return isFileNameValid(value);
    }

    static boolean isFileNameValid(String originalFileName) {
        if (originalFileName == null || originalFileName.isEmpty()) {
            return false;
        }
        String fileName = StringUtils.cleanPath(originalFileName);
        for (String illegalSymbol : ILLEGAL_SYMBOLS) {
            if (fileName.contains(illegalSymbol)) {
                return false;
            }
        }
        return true;
    }
}
