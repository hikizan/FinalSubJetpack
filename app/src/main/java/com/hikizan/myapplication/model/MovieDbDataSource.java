package com.hikizan.myapplication.model;

import androidx.lifecycle.LiveData;

import com.hikizan.myapplication.model.source.local.entity.MovieTvshowEntity;

import java.util.List;

public interface MovieDbDataSource {
    LiveData<List<MovieTvshowEntity>> getMovies(String checkId);

    LiveData<MovieTvshowEntity> getDetailMovies(String checkId, String moviesID);
}
