create table "address"
(
    id            serial primary key ,
    name          varchar(255),
    location_name varchar(255),
    longitude     double precision,
    latitude      double precision,
    home          varchar(255),
    user_id       int,
    foreign key (user_id) references "user"(id)
);
