CREATE TABLE users (
  id                         SERIAL              PRIMARY KEY
, name                       VARCHAR(100)        NOT NULL
, email                      VARCHAR(100)
, nick                       VARCHAR(100)
);