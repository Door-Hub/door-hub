create table "user_category"
(
    user_id     int,
    category_id int,
    primary key (user_id, category_id),
    foreign key (user_id) references "user" (id),
    foreign key (category_id) references category (id)
);