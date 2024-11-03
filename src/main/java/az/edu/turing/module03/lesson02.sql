--docker run --name homework_postgre -e POSTGRES_PASSWORD=root -p 5445:80 -d postgres
--docker start [enter id]
--docker exec -it [id] psql -U postgres
--CREATE DATABASE test;
--\c test
CREATE TABLE IF NOT EXISTS users(
id                  bigserial,
created_at          timestamptz(3)                          DEFAULT CURRENT_TIMESTAMP,
updated_at          timestamptz(3)                          DEFAULT CURRENT_TIMESTAMP,
updated_by          bigint,
email               varchar(50) UNIQUE NOT NULL,
name                varchar(20) NOT NULL,
surname             varchar(30) NOT NULL,
username            varchar(40) NOT NULL UNIQUE,
gender              varchar(6) NOT NULL CHECK (gender IN ('Male', 'Female', 'Other')),
age                 integer CHECK(age > 0),
is_active           boolean                                 DEFAULT TRUE,
PRIMARY KEY(ID),
CONSTRAINT fk_user FOREIGN KEY(updated_by) REFERENCES users(id));

CREATE TABLE IF NOT EXISTS posts(
id                  bigserial,
created_at          timestamptz(3)                          DEFAULT CURRENT_TIMESTAMP,
updated_at          timestamptz(3)                          DEFAULT CURRENT_TIMESTAMP,
created_by          bigint,
updated_by          bigint,
description         varchar(255),
PRIMARY KEY(id),
CONSTRAINT fk_user FOREIGN KEY(created_by) REFERENCES users(id),
CONSTRAINT fk_user2 FOREIGN KEY(updated_by) REFERENCES users(id));

CREATE TABLE IF NOT EXISTS comments(
id                  bigserial,
created_at          timestamptz(3)                          DEFAULT CURRENT_TIMESTAMP,
updated_at          timestamptz(3)                          DEFAULT CURRENT_TIMESTAMP,
created_by          bigint,
updated_by          bigint,
post_id             bigint,
context             varchar(255),
PRIMARY KEY(id),
CONSTRAINT fk_user FOREIGN KEY(created_by) REFERENCES users(id),
CONSTRAINT fk_user2 FOREIGN KEY(updated_by) REFERENCES users(id),
CONSTRAINT fk_post FOREIGN KEY(post_id) REFERENCES posts(id));

CREATE TABLE IF NOT EXISTS sub_comments(
id                  bigserial,
comment_id          bigint,
user_id             bigint,
context             varchar(255),
PRIMARY KEY(id),
CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES users(id),
CONSTRAINT fk_comment FOREIGN KEY(comment_id) REFERENCES comments(id));

CREATE TABLE IF NOT EXISTS post_likes(
liked_by            bigint,
post_id             bigint,
PRIMARY KEY(liked_by, post_id));

CREATE TABLE IF NOT EXISTS comment_likes(
liked_by            bigint,
comment_id          bigint,
PRIMARY KEY(liked_by, comment_id));

CREATE TABLE IF NOT EXISTS comment_dislikes(
liked_by            bigint,
comment_id          bigint,
PRIMARY KEY(liked_by, comment_id));



INSERT INTO users(name, surname, username, email, gender, age)
VALUES ('Semmed', 'Kosayev', 'ForeverOyuncu2', 'foreveroyuncu2@gmail.com', 'Male', 19);

INSERT INTO posts(created_by, description)
VALUES (1, 'The first post of the account. Supporting is higly valued.');

INSERT INTO post_likes(liked_by, post_id)
VALUES(1, 1);

INSERT INTO comments(created_by, post_id, context)
VALUES(1, 1, 'postu beyenib, yorum atanlar sagolsun');

INSERT INTO sub_comments(comment_id, user_id, context)
VALUES (1, 1, 'beyenmeyenlerinde cani sag olsun');

SELECT * FROM users;
SELECT * FROM posts;
SELECT * FROM comments;
SELECT * FROM sub_comments;
SELECT * FROM post_likes;
SELECT * FROM comment_likes;
SELECT * FROM comment_dislikes;