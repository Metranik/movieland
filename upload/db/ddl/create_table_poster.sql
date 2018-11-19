CREATE TABLE IF NOT EXISTS poster (
  id                         SERIAL              PRIMARY KEY
, movieId                    INT                 NOT NULL
, picturePath                VARCHAR(1000)       NOT NULL
);

ALTER TABLE IF EXISTS ONLY poster
  ADD CONSTRAINT "fk_movieId" FOREIGN KEY (movieId) REFERENCES movie(id);
  
CREATE TABLE IF NOT EXISTS raw_poster (
  nameRussian                VARCHAR(100)
, picturePath                VARCHAR(1000)
);