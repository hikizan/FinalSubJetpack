package com.hikizan.myapplication.model;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.hikizan.myapplication.model.source.local.entity.MovieTvshowEntity;
import com.hikizan.myapplication.vo.Resource;

import java.util.List;

public interface MovieDbDataSource {
    LiveData<Resource<PagedList<MovieTvshowEntity>>> getMovies(String checkId);

    LiveData<Resource<MovieTvshowEntity>> getDetailMovies(String checkId, String moviesID);

    LiveData<PagedList<MovieTvshowEntity>> getFavoritedMovies();

    LiveData<PagedList<MovieTvshowEntity>> getFavoritedTvshows();

    void setMovieTvshowsFavorite(MovieTvshowEntity movietvshow, boolean state);
}
