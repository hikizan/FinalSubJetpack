package com.hikizan.myapplication.model.source.local;

import androidx.lifecycle.LiveData;

import com.hikizan.myapplication.model.source.local.entity.MovieTvshowEntity;
import com.hikizan.myapplication.model.source.local.room.MovieDao;

import java.util.List;

public class LocalDataSource {

    private static LocalDataSource INSTANCE;
    private final MovieDao mMovieDao;

    private LocalDataSource(MovieDao mMovieDao) {
        this.mMovieDao = mMovieDao;
    }

    public static LocalDataSource getINSTANCE(MovieDao movieDao) {
        if (INSTANCE == null) {
            INSTANCE = new LocalDataSource(movieDao);
        }
        return INSTANCE;
    }

    public LiveData<List<MovieTvshowEntity>> getMovies() {
        return mMovieDao.getMovies();
    }

    public LiveData<List<MovieTvshowEntity>> getTvShows() {
        return mMovieDao.getTvShows();
    }

    public LiveData<List<MovieTvshowEntity>> getFavoritedMovies() {
        return mMovieDao.getFavoritedMovies();
    }

    public LiveData<List<MovieTvshowEntity>> getFavoritedTvshows() {
        return mMovieDao.getFavoritedTvshows();
    }

    public LiveData<MovieTvshowEntity> getMovieTvshowById(final String idfilm) {
        return mMovieDao.getMovieTvshowById(idfilm);
    }

    public void insertMoviesTvshows(List<MovieTvshowEntity> moviestvshows) {
        mMovieDao.insertMoviesTvshows(moviestvshows);
    }

    public void setMovieTvshowFavorite(MovieTvshowEntity movietvshow, boolean newState) {
        movietvshow.setFavorited(newState);
        mMovieDao.updateMovieTvshow(movietvshow);
    }
}