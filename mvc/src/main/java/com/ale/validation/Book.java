package com.ale.validation;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
/**
  *
  * @author alewu
  * @date 2020/7/19
  */
@Data
public class Book {
    private long id;

    @NotBlank
    @Size(min = 0, max = 20)
    private String title;

    @NotBlank
    @Size(min = 0, max = 30)
    private String author;
}
