package com.hikizan.myapplication.model.source;

import androidx.lifecycle.LiveData;

import com.hikizan.myapplication.model.source.local.entity.MovieDbModel;

import java.util.List;

public interface MovieDbDataSource {
    LiveData<List<MovieDbModel>> getMovies(String checkId);

    LiveData<MovieDbModel> getDetailMovies(String checkId, String moviesID);
}
