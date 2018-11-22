package com.art.uploader;

import java.io.*;
import java.text.MessageFormat;

public class RunUploader {
    private static String INSERT_GENRE = "INSERT INTO genre(name) VALUES(''{0}'');";
    private static String INSERT_USERS = "INSERT INTO users (name, email, nick) VALUES (''{0}'',''{1}'',''{2}'');";
    private static String INSERT_RAW_POSTER = "INSERT INTO raw_poster(nameRussian, picturePath) VALUES(''{0}'',''{1}'');";
    private static String INSERT_MOVIE = "INSERT INTO movie(nameRussian, nameNative, yearOfRelease, description, rating, price)" +
                                                    " VALUES(''{0}'',''{1}'',''{2}'',''{3}'',''{4}'',''{5}'');";
    private static String INSERT_RAW_MOVIE_TO_COUNTRY = "INSERT INTO raw_movie_to_country (nameRussian, country) VALUES (''{0}'',''{1}'');";
    private static String INSERT_RAW_MOVIE_TO_GENRE = "INSERT INTO raw_movie_to_genre (nameRussian, genre) VALUES (''{0}'',''{1}'');";
    private static String INSERT_RAW_REVIEW = "INSERT INTO raw_review (nameRussian, nameUser, comment) VALUES (''{0}'',''{1}'',''{2}'');";

    public static void main(String[] args) {
        try {
            parseGenre("src\\main\\resources\\genre.txt", "db\\data\\data_genre.sql");
            parseUsers("src\\main\\resources\\user.txt", "db\\data\\data_users.sql");
            parsePoster("src\\main\\resources\\poster.txt", "db\\data\\data_poster.sql");
            parseMovie("src\\main\\resources\\movie.txt", "db\\data\\data_movie.sql");
            parseReview("src\\main\\resources\\review.txt", "db\\data\\data_review.sql");
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    private static void parseGenre(String fileInName, String fileOutName) throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileInName));
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileOutName));) {
            String inLine;
            while ((inLine = reader.readLine()) != null) {
                if (!inLine.isEmpty()) {
                    String outLine = MessageFormat.format(INSERT_GENRE, inLine);
                    writer.write(outLine);
                    writer.newLine();
                }
            }
            writer.flush();
        }
    }

    private static void parseUsers(String fileInName, String fileOutName) throws IOException {
        final int USER_INFO_SIZE = 3;

        try(BufferedReader reader = new BufferedReader(new FileReader(fileInName));
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileOutName));) {
            String inLine;
            int count = 0;
            Object[] userInfo = new String[USER_INFO_SIZE];
            while ((inLine = reader.readLine()) != null) {
                if (!inLine.isEmpty()) {
                    userInfo[count] = inLine;
                    count++;
                    if(count == USER_INFO_SIZE) {
                        count = 0;
                        String outLine = MessageFormat.format(INSERT_USERS, userInfo);
                        writer.write(outLine);
                        writer.newLine();
                    }
                }
            }
            writer.flush();
        }
    }

    private static void parsePoster(String fileInName, String fileOutName) throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileInName));
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileOutName));) {
            String inLine;
            while ((inLine = reader.readLine()) != null) {
                if (!inLine.isEmpty()) {
                    String outLine = MessageFormat.format(INSERT_RAW_POSTER, inLine.split(" https:")[0], "https:"+inLine.split("https:")[1]);
                    writer.write(outLine);
                    writer.newLine();
                }
            }
            writer.flush();
        }
    }

    private static void parseMovie(String fileInName, String fileOutName) throws IOException {
        final int MOVIE_INFO_SIZE = 7;

        try(BufferedReader reader = new BufferedReader(new FileReader(fileInName));
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileOutName));) {
            String inLine;
            StringBuilder lineBuilder = new StringBuilder();
            int count = 0;
            String[] movieInfo = new String[MOVIE_INFO_SIZE];
            while ((inLine = reader.readLine()) != null) {
                if (!inLine.isEmpty()) {
                    lineBuilder.append(inLine);
                }
                else if (lineBuilder.length() != 0) {
                    movieInfo[count] = lineBuilder.toString();
                    lineBuilder.setLength(0);
                    count++;
                    if(count == MOVIE_INFO_SIZE) {
                        count = 0;
                        // movie
                        String nameRussian = movieInfo[0].split("/")[0];
                        String nameNative = movieInfo[0].split("/")[1].replace("'", "''") ;
                        String outLineMovie = MessageFormat.format(INSERT_MOVIE,
                                nameRussian,
                                nameNative,
                                movieInfo[1],
                                movieInfo[4],
                                movieInfo[5].substring("rating:".length()),
                                movieInfo[6].substring("price:".length())
                                );
                        writer.write(outLineMovie);
                        writer.newLine();
                        // country
                        String[] countries = movieInfo[2].split(", ");
                        for (String country: countries) {
                            String outLineCountry = MessageFormat.format(INSERT_RAW_MOVIE_TO_COUNTRY,
                                    movieInfo[0].split("/")[0],
                                    country
                            );
                            writer.write(outLineCountry);
                            writer.newLine();
                        }
                        // genre
                        String[] genres = movieInfo[3].split(", ");
                        for (String genre: genres) {
                            String outLineGenre = MessageFormat.format(INSERT_RAW_MOVIE_TO_GENRE,
                                    movieInfo[0].split("/")[0],
                                    genre
                            );
                            writer.write(outLineGenre);
                            writer.newLine();
                        }
                    }
                }
            }
            writer.flush();
        }
    }

    private static void parseReview(String fileInName, String fileOutName) throws IOException {
        final int REVIEW_INFO_SIZE = 3;

        try(BufferedReader reader = new BufferedReader(new FileReader(fileInName));
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileOutName));) {
            String inLine;
            StringBuilder lineBuilder = new StringBuilder();
            int count = 0;
            Object[] reviewInfo = new String[REVIEW_INFO_SIZE];
            while ((inLine = reader.readLine()) != null) {
                if (!inLine.isEmpty()) {
                    lineBuilder.append(inLine);
                }
                else if (lineBuilder.length() != 0) {
                    reviewInfo[count] = lineBuilder.toString();
                    lineBuilder.setLength(0);
                    count++;
                    if(count == REVIEW_INFO_SIZE) {
                        count = 0;
                        String outLine = MessageFormat.format(INSERT_RAW_REVIEW, reviewInfo);
                        writer.write(outLine);
                        writer.newLine();
                    }
                }
            }
            writer.flush();
        }
    }
}
