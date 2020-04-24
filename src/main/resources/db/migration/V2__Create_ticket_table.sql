create table tickets (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  name TEXT NOT NULL,
  phone_number TEXT NOT NULL,
  description TEXT NOT NULL,
  date TEXT NOT NULL
);

INSERT INTO sqlite_sequence (name, seq) VALUES ("tickets", 1);
