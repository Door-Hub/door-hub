create table "category"
(
    id          int primary key,
    name        varchar(255) not null,
    avatar      varchar(255),
    category_id int,
    user_id     int,
    foreign key (user_id) references "user" (id)
);
-- CREATE TABLE category_stars
-- (
--     category_id INT,
--     star_value  INT,
--     PRIMARY KEY (category_id, star_value),
--     FOREIGN KEY (category_id) REFERENCES category (id)
-- );


