create table "user_book"
(
    user_id int,
    book_id int,
    primary key (user_id),
    foreign key (user_id) references "user" (id),
    foreign key (book_id) references book(id)
);