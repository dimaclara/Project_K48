CREATE DATABASE todolist;

CREATE TABLE tasks(
    id integer primary key not null,
    title varchar(10),
    description varchar(50),
    status varchar(50),
    type varchar(10)

)