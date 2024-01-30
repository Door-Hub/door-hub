CREATE TABLE attachment
(
    id          SERIAL PRIMARY KEY,
    file_name   VARCHAR(255) NOT NULL,
    url         VARCHAR(255),
    file_type   VARCHAR(255),
    upload_time TIMESTAMP    NOT NULL,
    user_id int,
    category_id int
);
