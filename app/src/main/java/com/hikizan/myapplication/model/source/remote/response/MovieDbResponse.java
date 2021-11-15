package com.hikizan.myapplication.model.source.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieDbResponse implements Parcelable {
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

    public MovieDbResponse(String IDMovieDB, String title, String dateRelease, String rating, String userScore, String genre, String overview, String duration, String url, String image) {
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

    protected MovieDbResponse(Parcel in) {
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

    public static final Creator<MovieDbResponse> CREATOR = new Creator<MovieDbResponse>() {
        @Override
        public MovieDbResponse createFromParcel(Parcel in) {
            return new MovieDbResponse(in);
        }

        @Override
        public MovieDbResponse[] newArray(int size) {
            return new MovieDbResponse[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(IDMovieDB);
        parcel.writeString(title);
        parcel.writeString(dateRelease);
        parcel.writeString(rating);
        parcel.writeString(userScore);
        parcel.writeString(genre);
        parcel.writeString(overview);
        parcel.writeString(duration);
        parcel.writeString(url);
        parcel.writeString(image);
    }
}
