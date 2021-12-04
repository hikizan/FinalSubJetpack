package com.hikizan.myapplication.model;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hikizan.myapplication.model.source.local.entity.MovieTvshowEntity;
import com.hikizan.myapplication.model.source.remote.RemoteDataSource;
import com.hikizan.myapplication.model.source.remote.response.MovieDbResponse;

import java.util.ArrayList;
import java.util.List;

public class MovieDbRepository implements MovieDbDataSource {
    private volatile static MovieDbRepository INSTANCE = null;

    private final RemoteDataSource remoteDataSource;

    private MovieDbRepository(@NonNull RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public static MovieDbRepository getInstance(RemoteDataSource remoteData) {
        if (INSTANCE == null) {
            synchronized (MovieDbRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MovieDbRepository(remoteData);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<List<MovieTvshowEntity>> getMovies(String checkId) {
        MutableLiveData<List<MovieTvshowEntity>> mutableLiveData = new MutableLiveData<>();
        remoteDataSource.getMovies(checkId, movieDbResponses -> {
            ArrayList<MovieTvshowEntity> ListMovies = new ArrayList<>();
            for (MovieDbResponse response : movieDbResponses) {
                MovieTvshowEntity movie = new MovieTvshowEntity(
                        response.getIDMovieDB(),
                        response.getTitle(),
                        response.getDateRelease(),
                        response.getRating(),
                        response.getUserScore(),
                        response.getGenre(),
                        response.getOverview(),
                        response.getDuration(),
                        response.getUrl(),
                        response.getImage());

                ListMovies.add(movie);
            }
            mutableLiveData.postValue(ListMovies);
        });

        return mutableLiveData;
    }

    @Override
    public LiveData<MovieTvshowEntity> getDetailMovies(String checkId, String moviesID) {
        MutableLiveData<MovieTvshowEntity> mutableLiveData = new MutableLiveData<>();
        remoteDataSource.getMovies(checkId, movieDbResponses -> {
            MovieTvshowEntity movie = null;
            for (MovieDbResponse response : movieDbResponses) {
                if (response.getIDMovieDB().equals(moviesID)) {
                    movie = new MovieTvshowEntity(
                            response.getIDMovieDB(),
                            response.getTitle(),
                            response.getDateRelease(),
                            response.getRating(),
                            response.getUserScore(),
                            response.getGenre(),
                            response.getOverview(),
                            response.getDuration(),
                            response.getUrl(),
                            response.getImage());
                }
            }
            mutableLiveData.postValue(movie);
        });

        return mutableLiveData;
    }
}
