CREATE TABLE users (
  id                         SERIAL              PRIMARY KEY
, name                       VARCHAR(100)        NOT NULL
, email                      VARCHAR(100)        NOT NULL
, password_original          VARCHAR(100)
, password                   VARCHAR(100)
);