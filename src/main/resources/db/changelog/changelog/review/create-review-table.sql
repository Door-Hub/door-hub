CREATE TABLE review
(
    id                   SERIAL PRIMARY KEY,
    stars                INT,
    seen_users           INT,
    users_id             INT NOT NULL,
    parent_category_id   INT,
    FOREIGN KEY (users_id) REFERENCES "user" (id) ON DELETE CASCADE,
    FOREIGN KEY (parent_category_id) REFERENCES parent (id) ON DELETE SET NULL
);