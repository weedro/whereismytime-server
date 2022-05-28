\connect root root;
create database tracked_time_database with owner = root
    encoding = 'UTF8';

\connect tracked_time_database root
create table wasted_time
(
    id           bigserial primary key,
    user_token   text   not null,
    window_name  text   not null,
    process_name text   not null,
    wasted_time  bigint not null
);

create index index_wasted_time_user_id_window_name on wasted_time using btree (user_token, window_name);
create index index_wasted_time_user_id_process_name on wasted_time using btree (user_token, process_name);

\connect root root
create database authentication_database with owner = root
    encoding = 'UTF8';

\connect authentication_database root
create table authenticated_user
(
    id       bigserial primary key,
    token    text        not null,
    username text unique not null,
    password text        not null
);

create index index_authenticated_user on authenticated_user (token);
