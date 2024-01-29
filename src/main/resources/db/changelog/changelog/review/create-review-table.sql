CREATE TABLE "review"
(
    id                 SERIAL PRIMARY KEY,
    stars              INTEGER,
    seenUsers          INTEGER,
    users_id int references "user"(id) on delete cascade,
    parent_category_id int references parent(id) on delete cascade
);