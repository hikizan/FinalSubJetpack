package com.hikizan.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.hikizan.myapplication.model.MovieDbRepository;
import com.hikizan.myapplication.model.source.local.entity.MovieTvshowEntity;
import com.hikizan.myapplication.vo.Resource;

public class DetailViewModel extends ViewModel {
    private MovieDbRepository repository;
    private String movieID;

    public DetailViewModel(MovieDbRepository movieDbRepository) {
        this.repository = movieDbRepository;
    }

    public void setSelectedMovies(String film) {
        this.movieID = film;
    }

    public LiveData<Resource<MovieTvshowEntity>> getDetailMovies(String check) {
        LiveData<Resource<MovieTvshowEntity>> movieDbModel = repository.getDetailMovies(check, movieID);
        return movieDbModel;
    }
}
