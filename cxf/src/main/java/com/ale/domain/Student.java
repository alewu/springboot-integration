package com.ale.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
/**
  *  bean
  * @author alewu
  * @date 2019-08-02
  */
@Data
public class Student implements Serializable {
    @NotBlank(message = "name can't be blank!")
    private String name;
    private Date birthday;
    private String profession;
}
