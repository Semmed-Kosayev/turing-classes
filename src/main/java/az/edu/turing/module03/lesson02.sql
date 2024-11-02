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
display_name        varchar(40) NOT NULL,
gender              varchar(10) NOT NULL,
age                 integer CHECK(age > 0),
is_active           boolean                                 DEFAULT TRUE,
PRIMARY KEY(ID),
CONSTRAINT fk_user FOREIGN KEY(updated_by) REFERENCES users(id));

CREATE TABLE IF NOT EXISTS posts(
id                  bigserial,
created_at          timestamptz(3)                          DEFAULT CURRENT_TIMESTAMP,
updated_at          timestamptz(3)                          DEFAULT CURRENT_TIMESTAMP,
like_count          integer CHECK(like_count >= 0)          DEFAULT 0,
comment_count       integer CHECK(comment_count >= 0)       DEFAULT 0,
share_count         integer CHECK(share_count >= 0)         DEFAULT 0,
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
like_count          integer CHECK(like_count >= 0)          DEFAULT 0,
dislike_count       integer CHECK(dislike_count >= 0)       DEFAULT 0,
sub_comment_count   integer CHECK(sub_comment_count >= 0)   DEFAULT 0,
created_by          bigint,
updated_by          bigint,
post_id             bigint,
context             varchar(255),
PRIMARY KEY(id),
CONSTRAINT fk_user FOREIGN KEY(created_by) REFERENCES users(id),
CONSTRAINT fk_user2 FOREIGN KEY(updated_by) REFERENCES users(id),
CONSTRAINT fk_post FOREIGN KEY(post_id) REFERENCES posts(id));

--i could only imagine likes table as many-to-many table.
CREATE TABLE IF NOT EXISTS likes(
liked_by            bigint,
post_id             bigint,
PRIMARY KEY(liked_by, post_id));

INSERT INTO users(name, surname, display_name, email, gender, age)
VALUES ('Semmed', 'Kosayev', 'ForeverOyuncu2', 'foreveroyuncu2@gmail.com', 'MALE', 19);

INSERT INTO posts(created_by, description)
VALUES (1, 'The first post of the account. Supporting is higly valued.');

UPDATE posts
SET like_count = 1
WHERE created_by = 1;

INSERT INTO likes(liked_by, post_id)
VALUES(1, 1);

INSERT INTO comments(created_by, post_id, context)
VALUES(1, 1, 'postu beyenib, yorum atanlar sagolsun');

SELECT * FROM users;
SELECT * FROM posts;
SELECT * FROM comments;
SELECT * FROM likes;

--joining users and posts
SELECT p.id, p.like_count, p.comment_count, p.share_count, p.description, u.display_name FROM posts p
JOIN users u
ON p.created_by = u.id;

--joining users, posts, and comments
SELECT p.id, p.like_count, p.comment_count, p.share_count, p.description, u.display_name, c.context FROM posts p
JOIN users u
ON p.created_by = u.id
JOIN comments c
ON p.id = c.post_id;