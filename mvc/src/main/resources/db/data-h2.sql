DELETE FROM user;

INSERT INTO user (id, name, age, email) VALUES
(1, 'Jack', 18, 'jack@ityouknow.com'),
(2, 'Rose', 36, 'rose@ityouknow.com'),
(3, 'Bob', 28, 'bob@ityouknow.com'),
(4, 'Smile', 21, 'smile@ityouknow.com'),
(5, 'Alice', 24, 'alice@ityouknow.com');

DELETE FROM dept;

INSERT INTO dept (id, dept_name) VALUES
(1, 'marketing'),
(2, 'sales'),
(3, 'purchasing'),
(4, 'it'),
(5, 'hr'),
(6, 'engineering');