drop table t_user;

create table t_user
(
    uid           serial primary key,
    username      varchar(20),
    pwd           varchar(100),
    salt          varchar(100),
    phone         varchar(20),
    email         varchar(20),
    gender        integer,
    is_delete     integer,
    created_user  varchar(60),
    created_time  date,
    modified_user varchar(60),
    modified_time date
);

select * from t_user;
truncate table t_user;

create table tag(
                    id serial primary key ,
                    tag text,
                    allquestion int,
                    today int,
                    month int,
                    week int,
                    year int
);
drop table tag;