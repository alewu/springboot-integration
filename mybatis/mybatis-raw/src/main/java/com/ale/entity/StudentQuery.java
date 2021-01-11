package com.ale.entity;

import lombok.Data;

import java.util.List;

@Data
public class StudentQuery {
    private String name;
    private List<Integer> ids;
}
