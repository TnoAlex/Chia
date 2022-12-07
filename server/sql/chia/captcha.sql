create table captcha
(
    id          varchar(128) not null
        primary key,
    uid         varchar(128) not null,
    code        varchar(10)  not null,
    create_time timestamp    not null,
    status      int          not null
);

