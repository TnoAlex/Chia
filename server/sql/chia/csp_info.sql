create table csp_info
(
    id           varchar(128)  not null
        primary key,
    name         int           not null,
    state        tinyint       not null comment '0：未开始 -1：已结束 1：进行中',
    start_time   datetime      not null,
    end_time     datetime      not null,
    pre_quantity int default 0 null,
    quantity     int default 0 null,
    score_h      int default 0 null,
    score_a      int default 0 null
);

