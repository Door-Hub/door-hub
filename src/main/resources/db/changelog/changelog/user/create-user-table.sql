CREATE TYPE "role" AS ENUM ('USER', 'WORKER');

CREATE TABLE "user"
(
    id           SERIAL PRIMARY KEY ,
    firstname    VARCHAR(255) NOT NULL,
    lastname     VARCHAR(255) NOT NULL,
    avatar       VARCHAR(255),
    username     VARCHAR(255),
    phone_number VARCHAR(20) UNIQUE,
    email        VARCHAR(255) UNIQUE,
    gender       VARCHAR(20),
    birth_date   TIMESTAMP,
    created      TIMESTAMP,
    updated      TIMESTAMP,
    role         "role",
    book_id      INT,
    roles        VARCHAR(255) ARRAY
);