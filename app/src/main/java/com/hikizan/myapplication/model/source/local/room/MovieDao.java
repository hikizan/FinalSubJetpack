package com.hikizan.myapplication.model.source.local.room;

import androidx.lifecycle.LiveData;
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

    @Query(" SELECT * FROM movietvshowentities WHERE IDMovieDB LIKE 'm%' ")
    LiveData<List<MovieTvshowEntity>> getMovies();

    @Query(" SELECT * FROM movietvshowentities WHERE IDMovieDB LIKE 't%' ")
    LiveData<List<MovieTvshowEntity>> getTvShows();

    @Query(" SELECT * FROM movietvshowentities WHERE (IDMovieDB LIKE 'm%') AND (favorited = 1) ")
    LiveData<List<MovieTvshowEntity>> getFavoritedMovies();

    @Query(" SELECT * FROM movietvshowentities WHERE (IDMovieDB LIKE 't%') AND (favorited = 1) ")
    LiveData<List<MovieTvshowEntity>> getFavoritedTvshows();

    @Query(" SELECT * FROM movietvshowentities WHERE IDMovieDB = :idfilm")
    LiveData<MovieTvshowEntity> getMovieTvshowById(String idfilm);
}
