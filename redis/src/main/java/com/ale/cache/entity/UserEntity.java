package com.ale.cache.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5237730257103305078L;

    private Long id;
    private String userName;
    private String userSex;

}