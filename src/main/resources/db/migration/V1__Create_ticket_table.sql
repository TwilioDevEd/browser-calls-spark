create table tickets (
  id SERIAL UNIQUE not null PRIMARY KEY,
  name varchar(100) not null,
  phone_number varchar(15) not null,
  description text not null,
  date timestamp not null
);
