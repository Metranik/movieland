CREATE TABLE IF NOT EXISTS movie_to_genre (
  movieId                    INT                 NOT NULL
, genreId                    INT                 NOT NULL
);

ALTER TABLE IF EXISTS ONLY movie_to_genre
  ADD CONSTRAINT "fk_movieId" FOREIGN KEY (movieId) REFERENCES movie(id);
  
ALTER TABLE IF EXISTS ONLY movie_to_genre
  ADD CONSTRAINT "fk_genreId" FOREIGN KEY (genreId) REFERENCES genre(id);

CREATE TABLE IF NOT EXISTS raw_movie_to_genre (
  nameRussian                VARCHAR(100)        NOT NULL
, genre                      VARCHAR(100)        NOT NULL
);