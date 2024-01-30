CREATE TYPE "type_of_property" AS ENUM ('HOME','OFFICE','VILLA');
CREATE TABLE book
(
    id               SERIAL PRIMARY KEY,
    hourly_rate      DOUBLE PRECISION,
    start_date       DATE,
    start_time       TIMESTAMP,
    type_of_property VARCHAR(255),
    description      TEXT,
    accepted         BOOLEAN,
    worker_id        INT,
    booker_id        INT,
    FOREIGN KEY (worker_id) REFERENCES "user" (id) ON DELETE CASCADE,
    FOREIGN KEY (booker_id) REFERENCES "user" (id) ON DELETE CASCADE
);