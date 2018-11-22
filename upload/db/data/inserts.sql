INSERT INTO country (name)
SELECT DISTINCT country 
  FROM raw_movie_to_country;

INSERT INTO poster (movieId, picturePath)
SELECT m.id
     , t.picturePath
  FROM raw_poster t
  JOIN movie m ON m.nameRussian = t.nameRussian;

INSERT INTO movie_to_country (movieId, countryId)
SELECT m.id
     , c.id
  FROM raw_movie_to_country t
  JOIN movie m ON m.nameRussian = t.nameRussian
  JOIN country c ON c.name = t.country;
  
INSERT INTO movie_to_genre (movieId, genreId)
SELECT m.id
     , g.id
  FROM raw_movie_to_genre t
  JOIN movie m ON m.nameRussian = t.nameRussian
  JOIN genre g ON g.name = t.genre;  

INSERT INTO review (movieId, userId, comment)
SELECT m.id
     , u.id
     , t.comment
  FROM raw_review t
  JOIN movie m ON m.nameRussian = t.nameRussian
  JOIN users u ON u.name = t.nameUser;
  
COMMIT;