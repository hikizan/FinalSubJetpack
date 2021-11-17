package com.hikizan.myapplication.database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class FavoriteMovie implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

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

    @Ignore
    public FavoriteMovie(){

    }

    public FavoriteMovie(String IDMovieDB, String title, String dateRelease, String rating, String userScore, String genre, String overview, String duration, String url, String image) {
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

    private FavoriteMovie(Parcel in) {
        id = in.readInt();
        IDMovieDB = in.readString();
        title = in.readString();
        dateRelease = in.readString();
        rating = in.readString();
        userScore = in.readString();
        genre = in.readString();
        overview = in.readString();
        duration = in.readString();
        url = in.readString();
        image = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(IDMovieDB);
        dest.writeString(title);
        dest.writeString(dateRelease);
        dest.writeString(rating);
        dest.writeString(userScore);
        dest.writeString(genre);
        dest.writeString(overview);
        dest.writeString(duration);
        dest.writeString(url);
        dest.writeString(image);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FavoriteMovie> CREATOR = new Creator<FavoriteMovie>() {
        @Override
        public FavoriteMovie createFromParcel(Parcel in) {
            return new FavoriteMovie(in);
        }

        @Override
        public FavoriteMovie[] newArray(int size) {
            return new FavoriteMovie[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIDMovieDB() {
        return IDMovieDB;
    }

    public void setIDMovieDB(String IDMovieDB) {
        this.IDMovieDB = IDMovieDB;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateRelease() {
        return dateRelease;
    }

    public void setDateRelease(String dateRelease) {
        this.dateRelease = dateRelease;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getUserScore() {
        return userScore;
    }

    public void setUserScore(String userScore) {
        this.userScore = userScore;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
