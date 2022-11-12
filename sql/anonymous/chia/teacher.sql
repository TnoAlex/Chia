create table if not exists teacher
(
    id       varchar(128) not null
        primary key,
    name     varchar(10)  not null,
    password varchar(128) not null
);

