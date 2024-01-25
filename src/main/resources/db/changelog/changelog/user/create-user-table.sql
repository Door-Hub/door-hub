create table "user"
(
    id          int PRIMARY KEY generated always as identity,
    firstname   VARCHAR(255) not null,
    lastname    VARCHAR(255) not null,
    avatar      VARCHAR(255),
    username    VARCHAR(255),
    phone_number VARCHAR(20)  not null,
    email       VARCHAR(255),
    gender      VARCHAR(10),
    birth_date   timestamp,
    created     timestamp,
    updated     timestamp,
    book_id     int
);

