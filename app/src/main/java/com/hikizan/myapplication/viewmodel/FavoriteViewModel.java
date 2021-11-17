package com.hikizan.myapplication.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.hikizan.myapplication.database.FavoriteMovie;
import com.hikizan.myapplication.repository.FavoriteMovieRepository;

import java.util.List;

public class FavoriteViewModel extends ViewModel {
    private final FavoriteMovieRepository mFavoriteMovieRepository;

    public FavoriteViewModel(Application application) {
        mFavoriteMovieRepository = new FavoriteMovieRepository(application);
    }

    LiveData<List<FavoriteMovie>> getAllMovies() {
        return mFavoriteMovieRepository.getAllMovies();
    }

    LiveData<List<FavoriteMovie>> getAllTvShows() {
        return mFavoriteMovieRepository.getAllTvShows();
    }
}
