package com.hikizan.myapplication.model;

import androidx.lifecycle.LiveData;

import com.hikizan.myapplication.model.source.local.entity.MovieTvshowEntity;
import com.hikizan.myapplication.vo.Resource;

import java.util.List;

public interface MovieDbDataSource {
    LiveData<Resource<List<MovieTvshowEntity>>> getMovies(String checkId);

    LiveData<Resource<MovieTvshowEntity>> getDetailMovies(String checkId, String moviesID);

    LiveData<List<MovieTvshowEntity>> getFavoritedMovies();

    LiveData<List<MovieTvshowEntity>> getFavoritedTvshows();

    void setMovieTvshowsFavorite(MovieTvshowEntity movietvshow, boolean state);
}
