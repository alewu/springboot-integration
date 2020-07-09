package com.ale.validation;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * @author alewu
 * @date 2020/7/9
 * @see javax.validation.constraints.NotEmpty 用在集合类上面
 * 加了@NotEmpty的String类、Collection、Map、数组，是不能为null或者长度为0的(String Collection Map的isEmpty()方法)
 * <p>
 * @see javax.validation.constraints.NotBlank 只用于String,不能为null且trim()之后size>0
 * <p>
 * @see javax.validation.constraints.NotNull 不能为null，但可以为empty,没有Size的约束
 */
@Data
public class ValidationEntity {
    @NotEmpty(message = "姓名不能为空")
    private String name;

    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, message = "密码长度不能小于6位")
    private String password;


}
