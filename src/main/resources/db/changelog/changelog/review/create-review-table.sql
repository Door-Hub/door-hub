create table "review"
(
    id          int primary key,
    user_id     int,
    category_id int,
    starts      int,
    foreign key (user_id) references "user" (id),
    foreign key (category_id) references category (id)
);