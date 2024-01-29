CREATE TABLE discount
(
    id                 SERIAL PRIMARY KEY,
    percentage         integer,
    start_date         TIMESTAMP,
    end_date           TIMESTAMP,
    parent_category_id INT references parent(id) on delete cascade
);