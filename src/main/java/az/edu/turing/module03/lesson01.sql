CREATE DATABASE test;

CREATE TABLE students(
student_id smallint,
student_name varchar(40) NOT NULL,
student_surname varchar(80) NOT NULL,
PRIMARY KEY(student_id));

INSERT INTO students
VALUES (1, 'semmed', 'kosayev');
INSERT INTO students
VALUES (2, 'nermine', 'kosayeva');

SELECT * FROM students;

INSERT INTO students
VALUES (3, ‘oops’, 'oops');

DELETE FROM students
WHERE student_id = 3;

UPDATE students
SET student_surname = 'memmedli'
WHERE student_id = 2;

ALTER TABLE students
ADD student_mark smallint;

TRUNCATE students;
DROP TABLE students;

create table students(
student_id serial,
student_name varchar(40) NOT NULL,
primary key(student_id));

INSERT INTO students(student_name)
VALUES ('semmed');