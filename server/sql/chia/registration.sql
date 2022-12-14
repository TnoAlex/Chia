create table registration
(
    id         varchar(128)      not null
        primary key,
    csp_id     varchar(128)      not null,
    student_id varchar(128)      not null,
    score      int     default 0 null,
    type       tinyint           not null,
    miss       tinyint default 1 null,
    constraint csp_id
        unique (csp_id, student_id),
    constraint reg_to_csp
        foreign key (csp_id) references csp_info (id)
            on update cascade on delete cascade,
    constraint reg_to_student
        foreign key (student_id) references student (id)
            on update cascade on delete cascade
);

