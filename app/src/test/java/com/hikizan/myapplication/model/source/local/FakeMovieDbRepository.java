package com.hikizan.myapplication.model.source.local;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hikizan.myapplication.model.MovieDbDataSource;
import com.hikizan.myapplication.model.source.local.entity.MovieTvshowEntity;
import com.hikizan.myapplication.model.source.remote.RemoteDataSource;
import com.hikizan.myapplication.model.source.remote.response.MovieDbResponse;

import java.util.ArrayList;
import java.util.List;

public class FakeMovieDbRepository implements MovieDbDataSource {
    private volatile static FakeMovieDbRepository INSTANCE = null;

    private final RemoteDataSource remoteDataSource;

    FakeMovieDbRepository(@NonNull RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public LiveData<List<MovieTvshowEntity>> getMovies(String checkId) {
        MutableLiveData<List<MovieTvshowEntity>> courseResults = new MutableLiveData<>();
        remoteDataSource.getMovies(checkId, courseResponses -> {
            ArrayList<MovieTvshowEntity> courseList = new ArrayList<>();
            for (MovieDbResponse response : courseResponses) {
                MovieTvshowEntity course = new MovieTvshowEntity(
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

                courseList.add(course);
            }
            courseResults.postValue(courseList);
        });

        return courseResults;
    }

    @Override
    public LiveData<MovieTvshowEntity> getDetailMovies(String checkId, String moviesID) {
        MutableLiveData<MovieTvshowEntity> courseResults = new MutableLiveData<>();
        remoteDataSource.getMovies(checkId, courseResponses -> {
            MovieTvshowEntity course = null;
            for (MovieDbResponse response : courseResponses) {
                if (response.getIDMovieDB().equals(moviesID)) {
                    course = new MovieTvshowEntity(
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
            courseResults.postValue(course);
        });

        return courseResults;
    }
}
