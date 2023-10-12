package com.alewu.cache.jetcache.dto;

public class User {
    private static final long serialVersionUID = -5157877924507849953L;
    private long userId;
    private String userName;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
