create table teacher
(
    id       varchar(128) not null
        primary key,
    name     varchar(10)  not null,
    password varchar(128) not null,
    email    varchar(32)  not null
);
