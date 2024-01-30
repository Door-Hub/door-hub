CREATE TABLE user_categories
(
    user_id    INT,
    category_id INT,
    PRIMARY KEY (user_id, category_id),
    FOREIGN KEY (user_id) REFERENCES "user" (id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES parent (id) ON DELETE CASCADE
);


