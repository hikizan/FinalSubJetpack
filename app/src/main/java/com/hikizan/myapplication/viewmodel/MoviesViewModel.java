package com.hikizan.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.hikizan.myapplication.model.MovieDbRepository;
import com.hikizan.myapplication.model.source.local.entity.MovieTvshowEntity;
import com.hikizan.myapplication.vo.Resource;


public class MoviesViewModel extends ViewModel {
    private final MovieDbRepository repository;

    public MoviesViewModel(MovieDbRepository movieDbRepository) {
        this.repository = movieDbRepository;
    }

    public LiveData<Resource<PagedList<MovieTvshowEntity>>> getData() {
        return repository.getMovies("0");
    }
}
