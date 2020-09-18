DROP TABLE IF EXISTS student;

CREATE TABLE student
(
    id           BIGINT(20)  NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    student_name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    gender       INT(11)     NULL DEFAULT NULL COMMENT '性别',
    email        VARCHAR(30) NULL DEFAULT NULL COMMENT '邮件',
    PRIMARY KEY (id)
);
