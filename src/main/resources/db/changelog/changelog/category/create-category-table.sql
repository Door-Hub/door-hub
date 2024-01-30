CREATE TABLE category
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(255),
    avatar       VARCHAR(255),
    attachment_id INT,
    FOREIGN KEY (attachment_id) REFERENCES attachment (id) ON DELETE SET NULL
);

