create table "book"
(
  id int primary key generated always as identity ,
  booker int,
  worker int,
  hourlyRate double precision,
  startDate timestamp,
  endDate timestamp,
  accepted BOOLEAN,
  user_id int,
  foreign key (user_id) references "user"(id)
);