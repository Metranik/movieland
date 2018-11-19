CREATE TABLE IF NOT EXISTS movie (
  id                         SERIAL              PRIMARY KEY
, nameRussian                VARCHAR(100)        NOT NULL
, nameNative                 VARCHAR(100)        NOT NULL
, yearOfRelease              NUMERIC(4,0)
, rating                     DOUBLE PRECISION
, price                      DOUBLE PRECISION
, description                TEXT
);