package com.ale.validation;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author alewu
 * @date 2020/7/9
 */
public class FileValidator implements ConstraintValidator<File, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        return false;
    }
}
