package com.hikizan.myapplication.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.hikizan.myapplication.database.FavoriteMovie;
import com.hikizan.myapplication.repository.FavoriteMovieRepository;

import java.util.List;

public class FavoriteViewModel extends ViewModel {
    private final FavoriteMovieRepository mFavoriteMovieRepository;

    public FavoriteViewModel(Application application) {
        mFavoriteMovieRepository = new FavoriteMovieRepository(application);
    }

    //public LiveData<List<FavoriteMovie>> getAllMovies() {
    public LiveData<PagedList<FavoriteMovie>> getAllMovies() {
        //return mFavoriteMovieRepository.getAllMovies();
        return new LivePagedListBuilder<>(mFavoriteMovieRepository.getAllMovies(), 3).build();
    }

    public LiveData<List<FavoriteMovie>> getAllTvShows() {
        return mFavoriteMovieRepository.getAllTvShows();
    }
}
