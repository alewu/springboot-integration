DROP TABLE IF EXISTS student;

create table student
(
    id           bigint auto_increment comment '主键ID' primary key,
    class_id     int         not null default 0 comment '班级id',
    student_name varchar(30) null     default '' comment '姓名',
    gender       int         null     default 0 comment '性别',
    birthday     date        null comment '出生日期'
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