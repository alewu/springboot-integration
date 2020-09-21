DROP TABLE IF EXISTS employee;

CREATE TABLE employee
(
    id         BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    name       VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    age        INT(11)     NULL DEFAULT NULL COMMENT '年龄',
    email      VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    gmt_create datetime    not null,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS dept;
CREATE TABLE dept
(
    id         BIGINT(20)  NOT NULL auto_increment COMMENT '主键ID',
    dept_name  VARCHAR(30) NULL DEFAULT NULL COMMENT '部门名称',
    deleted    VARCHAR(30) NULL DEFAULT 0 COMMENT '是否删除',
    gmt_create datetime    not null,
    PRIMARY KEY (id)
);