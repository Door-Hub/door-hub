create table "user_permission"
(
    user_id     int,
    permissions varchar(255),
    primary key (user_id, permissions),
    foreign key (user_id) references "user" (id)
);