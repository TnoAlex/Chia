create table if not exists csp_info
(
    id           varchar(128) not null
        primary key,
    name         int          not null,
    state        tinyint      not null,
    start_time   datetime     not null,
    end_time     datetime     not null,
    pre_quantity int          not null,
    quantity     int          not null,
    score_h      int          not null,
    score_a      int          not null
);

