create table "user"
(
    id int PRIMARY KEY,
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    avatar VARCHAR(255),
    username VARCHAR(255),
    phoneNumber VARCHAR(20),
    email VARCHAR(255),
    gender VARCHAR(10),
    brithDate timestamp,
    created timestamp,
    update timestamp
)