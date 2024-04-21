create table if not exists houses
(
    id         uuid primary key,
    name       varchar(128) not null,
    user_login varchar(64) not null,
    deleted    boolean default false,
    created_at timestamp,
    updated_at timestamp
);
