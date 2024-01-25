
create table "user_reviews"
(
    user_id   int,
    review_id int,
    primary key (user_id, review_id),
    foreign key (user_id) references "user" (id),
    foreign key (review_id) references review (id)
);