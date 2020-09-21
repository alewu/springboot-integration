package com.ale.mybatis.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 自定义自动填充
 *
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
     *
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
        boolean createdBy = metaObject.hasSetter(CREATED_BY);
        // 获取用户id
        Integer userId = getUserId();
        if (createdBy) {
            this.strictInsertFill(metaObject, CREATED_BY, Integer.class, userId);
        }
        boolean modifiedBy = metaObject.hasSetter(MODIFIED_BY);
        if (modifiedBy && Objects.nonNull(userId)) {
            this.strictInsertFill(metaObject, MODIFIED_BY, Integer.class, userId);
        }
    }

    private Integer getUserId() {
        // todo 获取用户id逻辑
        return null;
    }

    /**
     * update操作时要填充的字段
     *
     * @param metaObject metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        boolean gmtModified = metaObject.hasSetter(GMT_MODIFIED);
        if (gmtModified) {
            strictUpdateFill(metaObject, GMT_MODIFIED, LocalDateTime.class, LocalDateTime.now());
        }

        boolean modifiedBy = metaObject.hasSetter(MODIFIED_BY);
        Integer userId = getUserId();
        if (modifiedBy && Objects.nonNull(userId)) {
            this.strictUpdateFill(metaObject, MODIFIED_BY, Integer.class, userId);
        }
    }
}
