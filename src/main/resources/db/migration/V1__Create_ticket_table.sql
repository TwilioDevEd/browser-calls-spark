create table ticket (
  id int not null PRIMARY KEY,
  name varchar(100) not null,
  phone_number varchar(15) not null,
  description varchar(255) not null,
  time timestamp not null
);
