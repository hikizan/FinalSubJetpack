package com.hikizan.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.hikizan.myapplication.model.MovieDbRepository;
import com.hikizan.myapplication.model.source.local.entity.MovieTvshowEntity;


import java.util.List;

public class FavoriteViewModel extends ViewModel {
    private MovieDbRepository repository;

    public FavoriteViewModel(MovieDbRepository movieDbRepository){
        this.repository = movieDbRepository;
    }

    public LiveData<List<MovieTvshowEntity>> getFavoriteTvShowList() {
        return repository.getFavoritedTvshows();
    }

    public LiveData<List<MovieTvshowEntity>> getFavoriteMovieList() {
        return repository.getFavoritedMovies();
    }
}
