package com.ale.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel("")
@Data
public class User {

    @ApiModelProperty(value = "主键ID")
    @NotNull(message = " cannot be null!")
    private Long userId;

    @ApiModelProperty(value = "姓名")
    @NotBlank(message = "name cannot be null!")
    private String userName;

    @ApiModelProperty(value = "年龄")
    @Min(value = 0, message = "Age should not be less than 0")
    @Max(value = 100, message = "Age should not be greater than 100")
    private Integer age;

    @ApiModelProperty(value = "邮箱")
    private String email;
}
