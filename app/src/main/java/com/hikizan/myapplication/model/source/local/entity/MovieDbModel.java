package com.hikizan.myapplication.model.source.local.entity;

public class MovieDbModel {
    private String IDMovieDB;
    private String title;
    private String dateRelease;
    private String rating;
    private String userScore;
    private String genre;
    private String overview;
    private String duration;
    private String url;
    private String image;

    public String getIDMovieDB() {
        return IDMovieDB;
    }

    public String getTitle() {
        return title;
    }

    public String getDateRelease() {
        return dateRelease;
    }

    public String getRating() {
        return rating;
    }

    public String getUserScore() {
        return userScore;
    }

    public String getGenre() {
        return genre;
    }

    public String getOverview() {
        return overview;
    }

    public String getDuration() {
        return duration;
    }

    public String getUrl() {
        return url;
    }

    public String getImage() {
        return image;
    }

    public MovieDbModel(String IDMovieDB, String title, String dateRelease, String rating, String userScore, String genre, String overview, String duration, String url, String image) {
        this.IDMovieDB = IDMovieDB;
        this.title = title;
        this.dateRelease = dateRelease;
        this.rating = rating;
        this.userScore = userScore;
        this.genre = genre;
        this.overview = overview;
        this.duration = duration;
        this.url = url;
        this.image = image;
    }
}
