package com.hikizan.myapplication.model.source.local.room;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.hikizan.myapplication.model.source.local.entity.MovieTvshowEntity;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMoviesTvshows(List<MovieTvshowEntity> moviestvshows);

    @Update
    void updateMovieTvshow(MovieTvshowEntity movietvshow);

    //@Query(" SELECT * FROM movietvshowentities")
    //LiveData<List<MovieTvshowEntity>> getAllMovieTvshow();

    @Query(" SELECT * FROM movietvshowentities WHERE IDMovieDB LIKE 'm%' ")
    DataSource.Factory<Integer, MovieTvshowEntity> getListMovie();
    //LiveData<List<MovieTvshowEntity>> getListMovie();

    @Query(" SELECT * FROM movietvshowentities WHERE IDMovieDB LIKE 't%' ")
    DataSource.Factory<Integer, MovieTvshowEntity> getListTvShow();
    //LiveData<List<MovieTvshowEntity>> getListTvShow();

    @Query(" SELECT * FROM movietvshowentities WHERE (IDMovieDB LIKE 'm%') AND (favorited = 1) ")
    DataSource.Factory<Integer, MovieTvshowEntity> getFavoritedMovies();
    //LiveData<List<MovieTvshowEntity>> getFavoritedMovies();

    @Query(" SELECT * FROM movietvshowentities WHERE (IDMovieDB LIKE 't%') AND (favorited = 1) ")
    DataSource.Factory<Integer, MovieTvshowEntity> getFavoritedTvshows();
    //LiveData<List<MovieTvshowEntity>> getFavoritedTvshows();

    @Query(" SELECT * FROM movietvshowentities WHERE IDMovieDB = :idfilm")
    LiveData<MovieTvshowEntity> getMovieTvshowById(String idfilm);
}
