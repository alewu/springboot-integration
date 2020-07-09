package com.ale.file;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author alewu
 * @date 2020/7/9
 */
public class MultipartFileValidator implements Validator {
    /**
     * This Validator validates *only* MultipartFile instances
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return MultipartFile.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "", "");
    }
}
