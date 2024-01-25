create table "user_attachment"
(
    user_id       int,
    attachment_id int,
    primary key (user_id, attachment_id),
    foreign key (user_id) references "user" (id),
    foreign key (attachment_id) references attachment (id)
);
