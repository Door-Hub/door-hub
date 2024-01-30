CREATE TABLE role_permission
(
    role_name       VARCHAR(255),
    permission_name VARCHAR(255),
    PRIMARY KEY (role_name, permission_name)
);
INSERT INTO role_permission (role_name, permission_name)
VALUES
    ('USER', 'CREATE'),
    ('USER', 'UPDATE'),
    ('USER', 'DELETE'),
    ('USER', 'GET_ALL'),
    ('WORKER', 'CREATE'),
    ('WORKER', 'UPDATE'),
    ('WORKER', 'DELETE'),
    ('WORKER', 'GET_ALL');
