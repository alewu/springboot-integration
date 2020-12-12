package com.ale.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageInfo implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -5492426587912966519L;

    public static final int PAGE = 1;
    public static final int PAGE_SIZE = 10;

    public static final String ORDER_BY = "";
    public static final Boolean ASC = true;

    /**
     * 总记录数
     */
    private Long total;
    /**
     * 分页大小
     */
    private Integer pageSize;
    /**
     * 当前页
     */
    private Integer currentPage;
    /**
     * 排序字段
     */
    @Deprecated
    private String orderBy;

    /**
     * 排序顺序
     */
    private Boolean asc;

    public PageInfo() {
        this(PAGE, PAGE_SIZE, null, null);
    }

    public PageInfo(Integer currentPage, Integer pageSize) {
        this(currentPage, pageSize, null, null);
    }

    public PageInfo(Integer currentPage, Integer pageSize, Long total) {
        this(currentPage, pageSize, total, null);
    }

    public PageInfo(Integer currentPage, Integer pageSize, String orderBy) {
        this(currentPage, pageSize, null, orderBy);
    }

    public PageInfo(Long currentPage, Long pageSize, Long total, String orderBy) {
        this(currentPage.intValue(), pageSize.intValue(), total, orderBy);
    }

    public PageInfo(Integer currentPage, Integer pageSize, Long total, String orderBy) {
        this.total = total;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.orderBy = orderBy;
    }



}