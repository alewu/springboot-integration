package com.ale.domain;

import lombok.Data;

import java.util.Date;
/**
  *  bean
  * @author alewu
  * @date 2019-08-02
  */
@Data
public class Person {
    private String name;
    private Date birthday;
    private String profession;
}
