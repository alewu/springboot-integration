package com.ale.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author alewu
 */
@Data
public class StudentQuery {
    private String name;
    private List<Integer> ids;
    private Date createTime;
}
