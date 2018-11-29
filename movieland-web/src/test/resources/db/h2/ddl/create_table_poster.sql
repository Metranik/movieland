CREATE TABLE poster (
  id                         SERIAL              PRIMARY KEY
, movieId                    INT                 NOT NULL
, picturePath                VARCHAR(1000)       NOT NULL
);