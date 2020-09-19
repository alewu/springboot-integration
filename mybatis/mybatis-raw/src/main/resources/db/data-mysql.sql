DELETE
FROM student;

INSERT INTO student (id, class_id, student_name, gender, birthday)
VALUES (1, 1, 'Jack', 1, '1993-11-11'),
       (2, 1, 'Rose', 0, '2000-12-11'),
       (3, 1, 'Bob', 1, '2001-05-13'),
       (4, 1, 'Smile', 0, '1923-06-16'),
       (5, 1, 'Alice', 0, '1956-11-11');


insert into course
values (1, '语文'),
       (2, '数学'),
       (3, '英语');

insert into score (student_id, course_id, score_value)
values (1, 1, 80),
       (1, 2, 90),
       (1, 3, 50),
       (2, 1, 60),
       (2, 2, 80),
       (2, 3, 82),
       (3, 1, 25),
       (3, 2, 86),
       (3, 3, 76),
       (4, 1, 94),
       (4, 2, 32),
       (4, 3, 40),
       (5, 1, 80),
       (5, 2, 77),
       (5, 3, 88),
       (6, 1, 50),
       (6, 2, 36),
       (6, 3, 96);




