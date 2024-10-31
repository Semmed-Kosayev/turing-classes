create table users(
id bigserial,
        created_at timestamptz(3) DEFAULT CURRENT_TIMESTAMP,
        updated_at timestamptz(3) DEFAULT CURRENT_TIMESTAMP,
        name varchar(20) NOT NULL,
        surname varchar(30) NOT NULL,
        display_name varchar(40) NOT NULL,
        gender varchar(10) NOT NULL,
        age integer check(age > 0),
        Primary Key(ID));

create table posts(
id bigserial,
        created_at timestamptz(3) DEFAULT CURRENT_TIMESTAMP,
        updated_at timestamptz(3) DEFAULT CURRENT_TIMESTAMP,
        like_count integer check(like_count >= 0),
comment_count integer check(comment_count >= 0),
share_count integer check(share_count >= 0),
created_by integer,
description varchar(255),
Primary Key(id),
Constraint fk_user FOREIGN KEY(created_by) REFERENCES users(id));

create table comments(
id bigserial,
        created_at timestamptz(3) DEFAULT CURRENT_TIMESTAMP,
        updated_at timestamptz(3) DEFAULT CURRENT_TIMESTAMP,
        like_count integer check(like_count >= 0),
dislike_count integer check(dislike_count >= 0),
sub_comment_count integer check(sub_comment_count >= 0),
created_by integer,
context varchar(255),
Primary Key(id),
Constraint fk_user FOREIGN KEY(created_by) REFERENCES users(id));

ALTER TABLE posts
ALTER COLUMN like_count SET DEFAULT 0;
ALTER TABLE posts
ALTER COLUMN comment_count SET DEFAULT 0;
ALTER TABLE posts
ALTER COLUMN share_count SET DEFAULT 0;

insert into posts(created_by, description) VALUES (1, 'The first post of the account. Supporting is higly valued.');
UPDATE posts
set like_count = 1
where created_by = 1;

ALTER TABLE comments
ALTER COLUMN like_count SET DEFAULT 0;
ALTER TABLE comments
ALTER COLUMN dislike_count SET DEFAULT 0;
ALTER TABLE comments
ALTER COLUMN sub_comment_count SET DEFAULT 0;

insert into comments(created_by, context) values(1, 'postu beyenib, yorum atanlar sagolsun');