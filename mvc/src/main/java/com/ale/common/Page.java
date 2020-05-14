package com.ale.common;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * @author alewu
 */
@Data
public class Page<E> implements Serializable {
    private static final long serialVersionUID = -5492426587912966519L;

    /**
     * 总数
     */
    @ApiModelProperty(value = "总记录数", hidden = true)
    private long total;
    /**
     * 每页显示条数，默认 10
     */
    @ApiModelProperty(value = "分页大小", example = "10")
    private long size = 10;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页", example = "1")
    private long current = 1;

    /**
     * 查询数据列表
     */
    private List<E> items = Collections.emptyList();

    /**
     * 排序字段信息
     */
    private List<OrderItem> orders = new ArrayList<>();


    public Page(){
    }

}
