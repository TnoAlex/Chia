create table pre_registration
(
    id         varchar(128) not null
        primary key,
    csp_id     varchar(128) not null,
    student_id varchar(128) not null,
    type       tinyint      not null,
    extra      varchar(100) not null,
    constraint pre_reg_to_csp
        foreign key (csp_id) references csp_info (id)
            on update cascade on delete cascade,
    constraint pre_reg_to_student
        foreign key (student_id) references student (id)
            on update cascade on delete cascade
);

