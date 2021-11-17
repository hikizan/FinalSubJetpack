package com.hikizan.myapplication.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.hikizan.myapplication.database.FavoriteMovie;
import com.hikizan.myapplication.repository.FavoriteMovieRepository;

public class DetailFavoriteViewModel extends ViewModel {
    private final FavoriteMovieRepository mFavoriteMovieRepository;

    public DetailFavoriteViewModel(Application application) {
        mFavoriteMovieRepository = new FavoriteMovieRepository(application);
    }

    public void insert(FavoriteMovie favoriteMovie) {
        mFavoriteMovieRepository.insert(favoriteMovie);
    }

    public void delete(FavoriteMovie favoriteMovie) {
        mFavoriteMovieRepository.delete(favoriteMovie);
    }

    public LiveData<FavoriteMovie> findSpecificFilm(String idfilm) {
        return mFavoriteMovieRepository.findSpecificFilm(idfilm);
    }

    public void dataToDelete(String idfilm) {
        mFavoriteMovieRepository.getFavoriteFilm(idfilm);
    }
}
