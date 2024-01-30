CREATE TABLE user_roles
(
    user_id INT,
    role    role,
    PRIMARY KEY (user_id, role),
    FOREIGN KEY (user_id) REFERENCES "user" (id) ON DELETE CASCADE
);


CREATE TABLE user_permissions
(
    user_id     INT,
    permission  VARCHAR(255),
    PRIMARY KEY (user_id, permission),
    FOREIGN KEY (user_id) REFERENCES "user" (id) ON DELETE CASCADE
);