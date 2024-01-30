CREATE TABLE user_reviews
(
    user_id  INT,
    review_id INT,
    PRIMARY KEY (user_id, review_id),
    FOREIGN KEY (user_id) REFERENCES "user" (id) ON DELETE CASCADE,
    FOREIGN KEY (review_id) REFERENCES review (id) ON DELETE CASCADE
);