CREATE TABLE "user_roles"
(
    user_id INT,
    role    VARCHAR(255),
    PRIMARY KEY (user_id, role),
    FOREIGN KEY (user_id) REFERENCES "user"(id),
    FOREIGN KEY (role) REFERENCES role (name)
);