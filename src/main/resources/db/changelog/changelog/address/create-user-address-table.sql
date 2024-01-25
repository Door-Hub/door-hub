create table "user_address"
(
    user_id    int,
    address_id int,
    primary key (user_id, address_id),
    foreign key (user_id) references "user" (id),
    foreign key (address_id) references address (id)
);