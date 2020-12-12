package com.ale.entity;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class User {

    @NotNull(message = " cannot be null!")
    private Long userId;

    @NotBlank(message = "name cannot be null!")
    private String userName;

    @Min(value = 0, message = "Age should not be less than 0")
    @Max(value = 100, message = "Age should not be greater than 100")
    private Integer age;

    private String email;

    private Integer status;
}
