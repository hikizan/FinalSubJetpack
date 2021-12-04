package com.hikizan.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.hikizan.myapplication.model.MovieDbRepository;
import com.hikizan.myapplication.model.source.local.entity.MovieTvshowEntity;

import java.util.List;

public class MoviesViewModel extends ViewModel {
    private MovieDbRepository repository;

    public MoviesViewModel(MovieDbRepository movieDbRepository) {
        this.repository = movieDbRepository;
    }

    public LiveData<List<MovieTvshowEntity>> getData() {
        return repository.getMovies("0");
    }
}
