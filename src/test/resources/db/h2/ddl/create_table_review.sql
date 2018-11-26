CREATE TABLE review (
  id                         SERIAL              PRIMARY KEY
, movieId                    INT                 NOT NULL
, userId                     INT                 NOT NULL
, comment                    TEXT                NOT NULL
);