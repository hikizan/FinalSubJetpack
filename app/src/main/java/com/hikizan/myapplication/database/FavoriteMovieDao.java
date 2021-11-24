package com.hikizan.myapplication.database;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoriteMovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(FavoriteMovie favoriteMovie);

    @Delete()
    void delete(FavoriteMovie favoriteMovie);

    @Query("SELECT * from favoritemovie WHERE IDMovieDB LIKE 'm%' ORDER BY id DESC")
    //LiveData<List<FavoriteMovie>> getAllMovies();
    DataSource.Factory<Integer, FavoriteMovie> getAllMovies();

    @Query("SELECT * from favoritemovie WHERE IDMovieDB LIKE 't%' ORDER BY id DESC")
    LiveData<List<FavoriteMovie>> getAllTvShows();

    @Query("SELECT * from favoritemovie WHERE IDMovieDB = :idfilm")
    LiveData<FavoriteMovie> findSpecificFilm(String idfilm);

    @Query("SELECT * from favoritemovie WHERE IDMovieDB = :idfilm")
    FavoriteMovie getFavoriteFilm(String idfilm);
}
