DROP TABLE IF EXISTS student;

create table student
(
    id           bigint auto_increment comment '主键ID' primary key,
    class_id     int         not null default 0 comment '班级id',
    student_name varchar(30) null     default '' comment '姓名',
    gender       int         null     default 0 comment '性别',
    birthday     date        null comment '出生日期',
    create_time  date        null comment '创建时间'
);


DROP TABLE IF EXISTS class;
create table class
(
    id         int unsigned auto_increment primary key,
    class_name varchar(255) default '' null
)
    comment '班级表';

DROP TABLE IF EXISTS course;
create table course
(
    id          int auto_increment primary key,
    course_name varchar(20) default '' not null comment '课程名称'

)
    comment '课程表';

DROP TABLE IF EXISTS score;
create table score
(
    id int auto_increment primary key,
    course_id   int not null comment '课程id',
    student_id  int not null comment '学生id',
    score_value int not null comment '分数值'
)
    comment '分数表';

DROP TABLE IF EXISTS t_sys_user;
CREATE TABLE t_sys_user
(
    `ID`              int                                                    NOT NULL,
    `USER_NAME`       varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `USER_CODE`       varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `USER_PASSWORD`   varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `VALID`           tinyint                                                NOT NULL,
    `LAST_LOGIN_IP`   varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最后登录ip',
    `LAST_LOGIN_TIME` datetime                                                DEFAULT NULL COMMENT '最后登录时间',
    `LOGIN_COUNT`     smallint                                                DEFAULT NULL COMMENT '登录次数',
    `CREATE_TIME`     datetime                                                DEFAULT NULL,
    `UPDATE_TIME`     datetime                                                DEFAULT NULL,
    PRIMARY KEY (`ID`) USING BTREE,
    KEY               `I_SYS_USER_CLUSTER` (`USER_CODE`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;