drop table course_schedules;
drop table study_schedules;
drop table exams;
drop table events;
drop table courses;
drop table users;


create table users (
    id        integer not null generated always as identity (start with 1, increment by 1),
    username  varchar(255) unique,
    password  varchar(255) not null,
    firstname varchar(255) not null,
    lastname  varchar(255) not null,
    primary key (id)
);

create table events (
    id             integer not null generated always as identity (start with 1, increment by 1),
    name           varchar(255),
    description    varchar(255),
    startdate      timestamp not null,
    enddate        timestamp not null,
    repeated       boolean default false,
    repeattype     varchar(255),
    repeatend      timestamp,

    constraint validate_date_range check (startdate < enddate),
    primary key (id)
);

create table courses (
    id       integer not null generated always as identity (start with 1, increment by 1),
    userid   integer,
    name     varchar(255),
    priority real default 1.0,
    gpa      real default 0.0,

    primary key (id),
    unique (userid, name),
    foreign key (userid) references users (id)
);

create table exams (
    courseid       integer,
    eventid        integer,
    weight         real default 1.0,
    grade          real default 0.0,

    primary key (courseid, eventid),
    constraint exams_courseid_fk foreign key (courseid) references courses (id),
    constraint exams_eventid_fk foreign key (eventid) references events (id)
);

create table course_schedules (
    courseid integer,
    eventid  integer,
    
    primary key (courseid, eventid),
    constraint course_schedule_courseid_fk foreign key (courseid) references courses (id),
    constraint course_schedule_eventid_fk foreign key (eventid) references events (id)
);

create table study_schedules (
    userid    integer not null,
    eventid   integer not null,

    primary key (userid, eventid),
    foreign key (userid) references users (id),
    foreign key (eventid) references events (id)
);
