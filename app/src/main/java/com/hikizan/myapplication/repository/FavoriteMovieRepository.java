package com.hikizan.myapplication.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.hikizan.myapplication.database.FavoriteMovie;
import com.hikizan.myapplication.database.FavoriteMovieDao;
import com.hikizan.myapplication.database.FavoriteMovieRoomDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoriteMovieRepository {
    private final FavoriteMovieDao mFavoriteMovieDao;
    private final ExecutorService executorService;
    FavoriteMovie favorited;

    public FavoriteMovieRepository(Application application) {
        executorService = Executors.newSingleThreadExecutor();
        FavoriteMovieRoomDatabase db = FavoriteMovieRoomDatabase.getDatabase(application);
        mFavoriteMovieDao = db.favoriteMovieDao();
    }

    public LiveData<List<FavoriteMovie>> getAllMovies() {
        return mFavoriteMovieDao.getAllMovies();
    }

    public LiveData<List<FavoriteMovie>> getAllTvShows() {
        return mFavoriteMovieDao.getAllTvShows();
    }

    public void insert(final FavoriteMovie favoriteMovie) {
        executorService.execute(() -> mFavoriteMovieDao.insert(favoriteMovie));
    }

    public void delete(final FavoriteMovie favoriteMovie) {
        executorService.execute(() -> mFavoriteMovieDao.delete(favoriteMovie));
    }

    public LiveData<FavoriteMovie> findSpecificFilm(String idfilm) {
        return mFavoriteMovieDao.findSpecificFilm(idfilm);
    }

    public void getFavoriteFilm(String idfilm) {
        executorService.execute(() -> {
            favorited = mFavoriteMovieDao.getFavoriteFilm(idfilm);
            if (favorited != null){
                mFavoriteMovieDao.delete(favorited);
            }
        });
    }

}
