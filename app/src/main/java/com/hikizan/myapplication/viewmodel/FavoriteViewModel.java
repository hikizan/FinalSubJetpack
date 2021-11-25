package com.hikizan.myapplication.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.hikizan.myapplication.database.FavoriteMovie;
import com.hikizan.myapplication.repository.FavoriteMovieRepository;


public class FavoriteViewModel extends ViewModel {
    private final FavoriteMovieRepository mFavoriteMovieRepository;

    public FavoriteViewModel(Application application) {
        mFavoriteMovieRepository = new FavoriteMovieRepository(application);
    }

    public LiveData<PagedList<FavoriteMovie>> getAllMovies() {
        return new LivePagedListBuilder<>(mFavoriteMovieRepository.getAllMovies(), 5).build();
    }

    public LiveData<PagedList<FavoriteMovie>> getAllTvShows() {
        return new LivePagedListBuilder<>(mFavoriteMovieRepository.getAllTvShows(), 5).build();
    }
}
