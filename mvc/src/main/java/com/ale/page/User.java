package com.ale.page;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@TableName("t_user")
public class User {

    @NotNull(message = " cannot be null!")
    @TableId
    private Long userId;

    @NotBlank(message = "name cannot be null!")
    private String userName;

    @Min(value = 0, message = "Age should not be less than 0")
    @Max(value = 100, message = "Age should not be greater than 100")
    private Integer age;

    private String email;
}
