package com.hikizan.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.hikizan.myapplication.model.MovieDbRepository;
import com.hikizan.myapplication.model.source.local.entity.MovieTvshowEntity;
import com.hikizan.myapplication.vo.Resource;

import java.util.List;

public class TvShowsViewModel extends ViewModel {
    private MovieDbRepository repository;

    public TvShowsViewModel(MovieDbRepository movieDbRepository) {
        this.repository = movieDbRepository;
    }

    public LiveData<Resource<List<MovieTvshowEntity>>> getData() {
        return repository.getMovies("1");
    }


}
