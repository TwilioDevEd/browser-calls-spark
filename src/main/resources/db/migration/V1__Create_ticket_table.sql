
DROP TABLE IF EXISTS tickets;

create table tickets (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  name TEXT not null,
  phone_number TEXT not null,
  description TEXT not null,
  date DATETIME not null
);

INSERT INTO sqlite_sequence (name, seq) VALUES ("tableSeq", 1);