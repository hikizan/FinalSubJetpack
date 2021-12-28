package com.hikizan.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.hikizan.myapplication.model.MovieDbRepository;
import com.hikizan.myapplication.model.source.local.entity.MovieTvshowEntity;


public class FavoriteViewModel extends ViewModel {
    private final MovieDbRepository repository;

    public FavoriteViewModel(MovieDbRepository movieDbRepository){
        this.repository = movieDbRepository;
    }

    public LiveData<PagedList<MovieTvshowEntity>> getFavoriteTvShowList() {
        return repository.getFavoritedTvshows();
    }

    public LiveData<PagedList<MovieTvshowEntity>> getFavoriteMovieList() {
        return repository.getFavoritedMovies();
    }
}
