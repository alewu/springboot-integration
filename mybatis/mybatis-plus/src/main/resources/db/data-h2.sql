DELETE
FROM employee;

INSERT INTO employee (id, name, age, email, gmt_create, gmt_modified, created_by, modified_by)
VALUES (1, 'Jack', 18, 'jack@ityouknow.com', '2020-10-10 00:00:00', '2020-10-10 00:00:00', 1, 1),
       (2, 'Rose', 36, 'rose@ityouknow.com', '2020-10-10 00:00:00', '2020-10-10 00:00:00', 1, 1),
       (3, 'Bob', 28, 'bob@ityouknow.com', '2020-10-10 00:00:00', '2020-10-10 00:00:00', 1, 1),
       (4, 'Smile', 21, 'smile@ityouknow.com', '2020-10-10 00:00:00', '2020-10-10 00:00:00', 1, 1),
       (5, 'Alice', 24, 'alice@ityouknow.com', '2020-10-10 00:00:00', '2020-10-10 00:00:00', 1, 1);

DELETE
FROM dept;

INSERT INTO dept (id, dept_name, gmt_create)
VALUES (1, 'marketing', '2020-10-10 00:00:00'),
       (2, 'sales', '2020-10-10 00:00:00'),
       (3, 'purchasing', '2020-10-10 00:00:00'),
       (4, 'it', '2020-10-10 00:00:00'),
       (5, 'hr', '2020-10-10 00:00:00'),
       (6, 'engineering', '2020-10-10 00:00:00');