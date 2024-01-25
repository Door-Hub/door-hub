create table "role_permission"
(
    role_name varchar(255),
    permission varchar(255),
    primary key (role_name, permission),
    foreign key (role_name) references "role" (name)
);