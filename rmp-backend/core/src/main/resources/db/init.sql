create table if not exists houses
(
    id         uuid primary key,
    name       varchar(128) not null,
    user_login varchar(64)  not null,
    deleted    boolean default false,
    created_at timestamp,
    updated_at timestamp
);

create table if not exists rooms
(
    id       uuid primary key,
    name     varchar(64) not null,
    house_id uuid references houses (id)
);

create table if not exists available_devices
(
    id uuid primary key,
    name varchar(64) not null,
    description varchar(256),
    type varchar(64) not null
);

create table if not exists rooms_devices
(
    id uuid primary key,
    name varchar(64) not null,
    room_id uuid references rooms(id),
    state boolean not null default false,
    type varchar(64) not null,
    trigger_amount integer,
    available_device_id uuid references available_devices(id),
    event_type text not null
);
