package com.hikizan.myapplication.model.source.local;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hikizan.myapplication.model.MovieDbDataSource;
import com.hikizan.myapplication.model.source.local.entity.MovieDbModel;
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
    public LiveData<List<MovieDbModel>> getMovies(String checkId) {
        MutableLiveData<List<MovieDbModel>> courseResults = new MutableLiveData<>();
        remoteDataSource.getMovies(checkId, courseResponses -> {
            ArrayList<MovieDbModel> courseList = new ArrayList<>();
            for (MovieDbResponse response : courseResponses) {
                MovieDbModel course = new MovieDbModel(
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
    public LiveData<MovieDbModel> getDetailMovies(String checkId, String moviesID) {
        MutableLiveData<MovieDbModel> courseResults = new MutableLiveData<>();
        remoteDataSource.getMovies(checkId, courseResponses -> {
            MovieDbModel course = null;
            for (MovieDbResponse response : courseResponses) {
                if (response.getIDMovieDB().equals(moviesID)) {
                    course = new MovieDbModel(
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
