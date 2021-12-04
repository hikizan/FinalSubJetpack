package com.hikizan.myapplication.model.source.local.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movietvshowentities")
public class MovieTvshowEntity {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "IDMovieDB")
    private String IDMovieDB;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "dateRelease")
    private String dateRelease;

    @ColumnInfo(name = "rating")
    private String rating;

    @ColumnInfo(name = "userScore")
    private String userScore;

    @ColumnInfo(name = "genre")
    private String genre;

    @ColumnInfo(name = "overview")
    private String overview;

    @ColumnInfo(name = "duration")
    private String duration;

    @ColumnInfo(name = "url")
    private String url;

    @ColumnInfo(name = "image")
    private String image;

    @ColumnInfo(name = "favorited")
    private boolean favorited = false;

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public void setIDMovieDB(@NonNull String IDMovieDB) {
        this.IDMovieDB = IDMovieDB;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDateRelease(String dateRelease) {
        this.dateRelease = dateRelease;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setUserScore(String userScore) {
        this.userScore = userScore;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImage(String image) {
        this.image = image;
    }

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

    public MovieTvshowEntity(String IDMovieDB, String title, String dateRelease, String rating, String userScore, String genre, String overview, String duration, String url, String image, Boolean favorited) {
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
        if (favorited != null){
            this.favorited = favorited;
        }
    }
}
