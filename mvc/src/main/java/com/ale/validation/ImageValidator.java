package com.ale.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * @author alewu
 * @date 2020/7/9
 */
@Slf4j
public class ImageValidator implements ConstraintValidator<File, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        if (value.isEmpty()) {
            ConstraintValidatorContext.ConstraintViolationBuilder builder =
                    context.buildConstraintViolationWithTemplate("image size can't be 0");
            builder.addConstraintViolation();
            return false;
        }
        if (Objects.equals(value.getContentType(), "png")) {
            ConstraintValidatorContext.ConstraintViolationBuilder builder =
                    context.buildConstraintViolationWithTemplate("upload file should be image");
            builder.addConstraintViolation();
            return false;
        }
        return true;
    }
}
