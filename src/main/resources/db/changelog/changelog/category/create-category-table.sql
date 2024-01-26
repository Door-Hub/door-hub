create table "category"
(
    id          int primary key,
    name        varchar(255) not null,
    avatar      varchar(255),
    stars       int,
    category_id int,
    user_id     int,
    foreign key (user_id) references "user" (id)
);



