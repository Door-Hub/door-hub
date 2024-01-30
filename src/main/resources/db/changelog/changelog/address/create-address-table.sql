CREATE TABLE address
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(255),
    location_name VARCHAR(255),
    home          VARCHAR(255),
    user_id int
);
