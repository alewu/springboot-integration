package com.ale.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("分页信息")
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
    @ApiModelProperty(value = "总记录数", hidden = true)
    private Long total;
    /**
     * 分页大小
     */
    @ApiModelProperty(value = "分页大小", example = "10")
    private Integer pageSize;
    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页", example = "1")
    private Integer currentPage;
    /**
     * 排序字段
     */
    @Deprecated
    @ApiModelProperty(value = "排序字段", example = "created_time")
    private String orderBy;

    /**
     * 排序顺序
     */
    @ApiModelProperty("升降序")
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