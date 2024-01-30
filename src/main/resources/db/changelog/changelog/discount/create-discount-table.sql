CREATE TABLE discount
(
    id                SERIAL PRIMARY KEY,
    percentage        INT,
    start_date        TIMESTAMP,
    end_date          TIMESTAMP,
    parent_category_id INT,
    FOREIGN KEY (parent_category_id) REFERENCES parent (id) ON DELETE SET NULL
);
