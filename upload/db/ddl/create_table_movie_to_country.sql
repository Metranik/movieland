CREATE TABLE IF NOT EXISTS movie_to_country (
  movieId                    INT                 NOT NULL
, countryId                  INT                 NOT NULL
);

ALTER TABLE IF EXISTS ONLY movie_to_country
  ADD CONSTRAINT "fk_movieId" FOREIGN KEY (movieId) REFERENCES movie(id);
  
ALTER TABLE IF EXISTS ONLY movie_to_country
  ADD CONSTRAINT "fk_countryId" FOREIGN KEY (countryId) REFERENCES country(id);

CREATE TABLE IF NOT EXISTS raw_movie_to_country (
  nameRussian                VARCHAR(100)        NOT NULL
, country                    VARCHAR(100)        NOT NULL
);