CREATE TABLE parent
(
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(255),
    avatar  VARCHAR(255),
    category_id INT,
    user_id INT,
    FOREIGN KEY (category_id) REFERENCES category(id)
);