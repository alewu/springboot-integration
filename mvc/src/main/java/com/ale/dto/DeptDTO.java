package com.ale.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class DeptDTO implements Serializable {
    private Integer deptId;
    private String name;
}
