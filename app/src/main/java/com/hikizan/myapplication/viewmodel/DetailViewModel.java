package com.hikizan.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.hikizan.myapplication.model.MovieDbRepository;
import com.hikizan.myapplication.model.source.local.entity.MovieTvshowEntity;

public class DetailViewModel extends ViewModel {
    private MovieDbRepository repository;
    private String movieID;

    public DetailViewModel(MovieDbRepository movieDbRepository) {
        this.repository = movieDbRepository;
    }

    public void setSelectedMovies(String film) {
        this.movieID = film;
    }

    public LiveData<MovieTvshowEntity> getDetailMovies(String check) {
        LiveData<MovieTvshowEntity> movieDbModel = repository.getDetailMovies(check, movieID);
        return movieDbModel;
    }
}
