create table if not exists captcha
(
    id          varchar(128) not null
        primary key,
    uid         varchar(128) not null,
    code        varchar(10)  not null,
    create_time timestamp    not null,
    status      int          not null
);

create table if not exists csp_info
(
    id             varchar(128)  not null
        primary key,
    name           int           not null,
    state          tinyint       not null comment '0：未开始 -1：已结束 1：进行中 ',
    start_time     datetime      not null,
    end_time       datetime      not null,
    pre_quantity   int default 0 null,
    quantity       int default 0 null,
    score_h        int default 0 null,
    score_a        int default 0 null,
    stage          int default 0 not null comment '0：全部未导入 1：预报名名单导入 2：成绩导入',
    free_threshold int           not null
);

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

create table if not exists student
(
    id              varchar(128) not null
        primary key,
    name            varchar(10)  not null,
    password        varchar(128) not null,
    student_number  varchar(15)  not null,
    id_number       varchar(20)  not null,
    max_score       int          null,
    free_times      int          not null,
    email           varchar(20)  null,
    enrollment_time date         not null,
    status          int          not null
);

create table if not exists pre_registration
(
    id         varchar(128) not null
        primary key,
    csp_id     varchar(128) not null,
    student_id varchar(128) not null,
    type       tinyint      not null comment '0 自费 1 免费',
    extra      varchar(100) not null,
    constraint pre_reg_to_csp
        foreign key (csp_id) references csp_info (id)
            on update cascade on delete cascade,
    constraint pre_reg_to_student
        foreign key (student_id) references student (id)
            on update cascade on delete cascade
);

create definer = root@localhost trigger pre_num_d
    after delete
    on pre_registration
    for each row
    UPDATE csp_info
    SET csp_info.pre_quantity = csp_info.pre_quantity - 1
    WHERE csp_info.id = old.csp_id;

create definer = root@localhost trigger pre_num_i
    after insert
    on pre_registration
    for each row
    UPDATE csp_info
    SET csp_info.pre_quantity = csp_info.pre_quantity + 1
    WHERE csp_info.id = new.csp_id;

create table if not exists registration
(
    id         varchar(128)      not null
        primary key,
    csp_id     varchar(128)      not null,
    student_id varchar(128)      not null,
    score      int     default 0 null,
    miss       tinyint default 1 null,
    type       tinyint           not null,
    constraint csp_id
        unique (csp_id, student_id),
    constraint reg_to_csp
        foreign key (csp_id) references csp_info (id)
            on update cascade on delete cascade,
    constraint reg_to_student
        foreign key (student_id) references student (id)
            on update cascade on delete cascade
);

create definer = root@localhost trigger reg_auto_max_i
    after insert
    on registration
    for each row
    UPDATE csp_info
    SET csp_info.score_h = (SELECT MAX(score) FROM registration)
    WHERE csp_info.id = new.csp_id;

create definer = root@localhost trigger reg_auto_max_u
    after update
    on registration
    for each row
    UPDATE csp_info
    SET csp_info.score_h = (SELECT MAX(score) FROM registration)
    WHERE csp_info.id = new.csp_id;

create definer = root@localhost trigger reg_auto_mean_i
    after insert
    on registration
    for each row
    UPDATE csp_info
    SET csp_info.score_a = (SELECT AVG(score) FROM registration as r WHERE r.csp_id = new.csp_id)
    WHERE csp_info.id = new.csp_id;

create definer = root@localhost trigger reg_auto_mean_u
    after update
    on registration
    for each row
    UPDATE csp_info
    SET csp_info.score_a = (SELECT AVG(score) FROM registration as r WHERE r.csp_id = new.csp_id)
    WHERE csp_info.id = new.csp_id;

create definer = root@localhost trigger reg_num_d
    after delete
    on registration
    for each row
    UPDATE csp_info
    SET csp_info.quantity = csp_info.quantity - 1
    WHERE csp_info.id = old.csp_id;

create definer = root@localhost trigger reg_num_i
    after insert
    on registration
    for each row
    UPDATE csp_info
    SET csp_info.quantity = csp_info.quantity + 1
    WHERE csp_info.id = new.csp_id;

create definer = root@localhost trigger student_auto_max_i
    after insert
    on registration
    for each row
    UPDATE student as s
    SET s.max_score = IF((SELECT score FROM registration as r WHERE r.student_id = s.id) > s.max_score,
                         (SELECT score FROM registration as r WHERE r.student_id = s.id), s.max_score);

create definer = root@localhost trigger student_auto_max_u
    after update
    on registration
    for each row
    UPDATE student as s
    SET s.max_score = IF((SELECT score FROM registration as r WHERE r.student_id = s.id) > s.max_score,
                         (SELECT score FROM registration as r WHERE r.student_id = s.id), s.max_score);

create table if not exists teacher
(
    id       varchar(128) not null
        primary key,
    name     varchar(10)  not null,
    password varchar(128) not null,
    email    varchar(32)  not null
);

