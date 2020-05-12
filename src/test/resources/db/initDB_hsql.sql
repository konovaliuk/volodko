DROP TABLE tickets
IF EXISTS;
DROP TABLE shows
IF EXISTS;
DROP TABLE user_roles
IF EXISTS;
DROP TABLE users
IF EXISTS;

CREATE TABLE users (
  id        INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY,
  firstname VARCHAR(40) NOT NULL,
  lastname  VARCHAR(40) NOT NULL,
  email     VARCHAR(40) NOT NULL UNIQUE,
  password  VARCHAR(40) NOT NULL
);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR(40),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id)
    ON DELETE CASCADE
);

CREATE TABLE shows (
  id    INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY,
  day   VARCHAR(9)  NOT NULL,
  time  VARCHAR(5)  NOT NULL,
  movie VARCHAR(40) NOT NULL,
  CONSTRAINT shows_idx UNIQUE (day, time)
);

CREATE TABLE tickets (
  id      INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1, INCREMENT BY 1) PRIMARY KEY,
  line     INTEGER NOT NULL,
  seat    INTEGER NOT NULL,
  price   INTEGER NOT NULL,
  sold    BOOLEAN DEFAULT FALSE,
  user_id INTEGER,
  show_id INTEGER NOT NULL,
  CONSTRAINT show_tickets_idx UNIQUE (line, seat, show_id),
  FOREIGN KEY (user_id) REFERENCES users (id)
    ON DELETE CASCADE,
  FOREIGN KEY (show_id) REFERENCES shows (id)
    ON DELETE CASCADE
);