DROP TABLE IF EXISTS employee;

CREATE TABLE employee
(
    id           BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    name         VARCHAR(30) NULL     DEFAULT NULL COMMENT '姓名',
    age          INT(11)     NULL     DEFAULT NULL COMMENT '年龄',
    email        VARCHAR(50) NULL     DEFAULT NULL COMMENT '邮箱',
    gmt_create   datetime    not null DEFAULT CURRENT_TIMESTAMP,
    gmt_modified datetime    not null DEFAULT CURRENT_TIMESTAMP,
    created_by   int         not null DEFAULT 0,
    modified_by  int         not null DEFAULT 0,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS dept;
CREATE TABLE dept
(
    id         BIGINT(20)  NOT NULL auto_increment COMMENT '主键ID',
    dept_name  VARCHAR(30) NULL     DEFAULT NULL COMMENT '部门名称',
    deleted    VARCHAR(30) NULL     DEFAULT 0 COMMENT '是否删除',
    gmt_create datetime    not null DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);