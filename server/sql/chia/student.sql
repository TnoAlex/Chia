create table student
(
    id              varchar(128) not null
        primary key,
    name            varchar(10)  not null,
    password        varchar(128) not null,
    student_number  varchar(15)  not null,
    id_number       varchar(20)  not null,
    max_score       int          null,
    free_times      int          not null,
    email           varchar(20)  not null,
    enrollment_time date         not null,
    status          int          not null
);

