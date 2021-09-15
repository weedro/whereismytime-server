\connect root root;
CREATE DATABASE trackedtime_database WITH OWNER = root
    ENCODING = 'UTF8';

\connect trackedtime_database root
create table wasted_time
(
    id          bigserial,
    user_id     varchar(50)  not null,
    window_name varchar(150) not null,
    wasted_time bigint       not null,
    constraint pk_wasted_time_id primary key (id)
);

create index index_wasted_time_user_id_window_name on wasted_time using btree (user_id, window_name);
