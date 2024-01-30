CREATE TABLE user_addresses
(
    user_id    INT,
    address_id INT,
    PRIMARY KEY (user_id, address_id),
    FOREIGN KEY (user_id) REFERENCES "user" (id) ON DELETE CASCADE,
    FOREIGN KEY (address_id) REFERENCES address (id) ON DELETE CASCADE
);