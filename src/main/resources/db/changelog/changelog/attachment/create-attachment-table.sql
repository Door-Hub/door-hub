create table "attachment"
(
    id               int primary key,
    fileName         varchar(255),
    originalFileName varchar(255),
    url              varchar(255),
    fileType         varchar(255),
    user_id          int,
    foreign key (user_id) references "user"(id)
);