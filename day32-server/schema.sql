drop database if exists todos;
create database todos;
use todos;
create table tasks (
    tid int auto_increment not null,
    username varchar(64) not null,
    task_name varchar(256) not null,
    priority enum('low', 'med', 'high') default 'low',
    due_date date,
    primary key (tid)
);

create table user (
    username varchar(64) not null,
    password varchar(512) not null,
    primary key (username)
);

/* 
To create a new user, use e.g.
insert into user (username, password) values ('eddie', sha1('eddie'));
 */