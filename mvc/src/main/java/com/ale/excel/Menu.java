package com.ale.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author alewu
 * @date 2020/10/13
 */
@Data
public class Menu {
    @ExcelProperty("公众号")
    private String appId;

    @ExcelProperty("一级菜单")
    private String firstLevel;

    @ExcelProperty("二级菜单")
    private String secondLevel;

    @ExcelProperty("点击次数")
    private int clickTimes;

    @ExcelProperty("点击人数")
    private int clickUserQty;

    @ExcelProperty("版本")
    private int version;


}
