\connect root root;
create database trackedtime_database with owner = root
    encoding = 'UTF8';

\connect trackedtime_database root
create table wasted_time
(
    id          bigserial,
    user_token  varchar(128) not null,
    window_name varchar(150) not null,
    wasted_time bigint       not null,
    constraint pk_wasted_time_id primary key (id)
);

create index index_wasted_time_user_id_window_name on wasted_time using btree (user_token, window_name);

\connect root root
create database authentication_database with owner = root
    encoding = 'UTF8';

\connect authentication_database root
create table authenticated_user
(
    id       bigserial,
    token    varchar(32)       not null,
    username varchar(30) unique not null,
    password varchar(128)       not null,
    constraint pk_authenticated_user_id primary key (id)
);
