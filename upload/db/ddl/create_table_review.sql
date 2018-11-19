CREATE TABLE IF NOT EXISTS review (
  id                         SERIAL              PRIMARY KEY
, movieId                    INT                 NOT NULL
, userId                     INT                 NOT NULL
, comment                    TEXT                NOT NULL
);

ALTER TABLE IF EXISTS ONLY review
  ADD CONSTRAINT "fk_movieId" FOREIGN KEY (movieId) REFERENCES movie(id);

ALTER TABLE IF EXISTS ONLY review
  ADD CONSTRAINT "fk_userId" FOREIGN KEY (userId) REFERENCES users(id);
  
CREATE TABLE IF NOT EXISTS raw_review (
  nameRussian                VARCHAR(100)        NOT NULL
, nameUser                   VARCHAR(100)        NOT NULL
, comment                    TEXT                NOT NULL
);