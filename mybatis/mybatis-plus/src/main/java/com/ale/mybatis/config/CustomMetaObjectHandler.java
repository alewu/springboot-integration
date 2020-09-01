package com.ale.mybatis.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自定义自动填充
 * @author alewu
 */
@Slf4j
@Component
public class CustomMetaObjectHandler implements MetaObjectHandler {
    private static final String GMT_CREATE = "gmtCreate";
    private static final String GMT_MODIFIED = "gmtModified";
    private static final String CREATED_BY = "createdBy";
    private static final String MODIFIED_BY = "modifiedBy";

    /**
     * insert操作时要填充的字段
     * @param metaObject metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 先判断是否存在该字段，目的是有些数据库的表没有创建时间和修改时间字段，如果再执行这些操作有点浪费
        boolean gmtCreate = metaObject.hasSetter(GMT_CREATE);
        if (gmtCreate) {
            strictInsertFill(metaObject, GMT_CREATE, LocalDateTime.class, LocalDateTime.now());
        }
        boolean gmtModified = metaObject.hasSetter(GMT_MODIFIED);
        if (gmtModified) {
            strictInsertFill(metaObject, GMT_MODIFIED, LocalDateTime.class, LocalDateTime.now());
        }
    }
    /**
     * update操作时要填充的字段
     * @param metaObject metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        boolean gmtModified = metaObject.hasSetter(GMT_MODIFIED);
        if (gmtModified) {
            strictUpdateFill(metaObject, GMT_MODIFIED, LocalDateTime.class, LocalDateTime.now());
        }

    }
}
