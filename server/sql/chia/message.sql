create table if not exists message
(
    id      varchar(128)  not null
        primary key,
    time    timestamp     not null,
    content blob          not null,
    deli_id varchar(128)  not null,
    reci_id varchar(128)  not null,
    status  int default 0 not null
);

