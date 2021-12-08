package com.hikizan.myapplication.model;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hikizan.myapplication.model.source.local.LocalDataSource;
import com.hikizan.myapplication.model.source.local.entity.MovieTvshowEntity;
import com.hikizan.myapplication.model.source.remote.ApiResponse;
import com.hikizan.myapplication.model.source.remote.RemoteDataSource;
import com.hikizan.myapplication.model.source.remote.response.MovieDbResponse;
import com.hikizan.myapplication.utils.AppExecutors;
import com.hikizan.myapplication.vo.Resource;

import java.util.ArrayList;
import java.util.List;

public class MovieDbRepository implements MovieDbDataSource {
    private volatile static MovieDbRepository INSTANCE = null;

    private final RemoteDataSource remoteDataSource;
    private final LocalDataSource localDataSource;
    private final AppExecutors appExecutors;

    private MovieDbRepository(@NonNull RemoteDataSource remoteDataSource, @NonNull LocalDataSource localDataSource, AppExecutors appExecutors) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
        this.appExecutors = appExecutors;
    }

    public static MovieDbRepository getInstance(RemoteDataSource remoteData, LocalDataSource localDataSource, AppExecutors appExecutors) {
        if (INSTANCE == null) {
            synchronized (MovieDbRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MovieDbRepository(remoteData, localDataSource, appExecutors);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<Resource<List<MovieTvshowEntity>>> getMovies(String checkId) {
        /*
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
         */

        return new NetworkBoundResource<List<MovieTvshowEntity>, List<MovieDbResponse>>(appExecutors){

            @Override
            protected LiveData<List<MovieTvshowEntity>> loadFromDB() {
                return localDataSource.getAllMovieTvshow();
            }

            @Override
            protected Boolean shouldFetch(List<MovieTvshowEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<MovieDbResponse>>> createCall() {
                return remoteDataSource.getMovies(checkId);
            }

            @Override
            protected void saveCallResult(List<MovieDbResponse> movieTvshowResponses) {
                ArrayList<MovieTvshowEntity> movieTvshowList = new ArrayList<>();
                for (MovieDbResponse response : movieTvshowResponses) {
                    MovieTvshowEntity movieTvshow = new MovieTvshowEntity(response.getIDMovieDB(),
                            response.getTitle(),
                            response.getDateRelease(),
                            response.getRating(),
                            response.getUserScore(),
                            response.getGenre(),
                            response.getOverview(),
                            response.getDuration(),
                            response.getUrl(),
                            response.getImage(),
                            false);
                    movieTvshowList.add(movieTvshow);
                }
                localDataSource.insertMoviesTvshows(movieTvshowList);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<MovieTvshowEntity>> getDetailMovies(String checkId, String moviesID) {
        /*
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
         */

        return new NetworkBoundResource<MovieTvshowEntity, MovieDbResponse>(appExecutors){

            @Override
            protected LiveData<MovieTvshowEntity> loadFromDB() {
                return localDataSource.getMovieTvshowById(moviesID);
            }

            @Override
            protected Boolean shouldFetch(MovieTvshowEntity movietvshow) {
                return (movietvshow == null);
            }

            @Override
            protected LiveData<ApiResponse<MovieDbResponse>> createCall() {
                return remoteDataSource.getDetailMovie(checkId, moviesID);
            }

            @Override
            protected void saveCallResult(MovieDbResponse data) {
                MovieTvshowEntity movieTvshowEntity = new MovieTvshowEntity(data.getIDMovieDB(),
                        data.getTitle(),
                        data.getDateRelease(),
                        data.getRating(),
                        data.getUserScore(),
                        data.getGenre(),
                        data.getOverview(),
                        data.getDuration(),
                        data.getUrl(),
                        data.getImage(),
                        false);
                localDataSource.updateContent(movieTvshowEntity);

            }
        }.asLiveData();
    }

    @Override
    public LiveData<List<MovieTvshowEntity>> getFavoritedMovies() {
        return localDataSource.getFavoritedMovies();
    }

    @Override
    public LiveData<List<MovieTvshowEntity>> getFavoritedTvshows() {
        return localDataSource.getFavoritedTvshows();
    }

    @Override
    public void setMovieTvshowsFavorite(MovieTvshowEntity movietvshow, boolean state) {
        appExecutors.diskIO().execute(() -> localDataSource.setMovieTvshowFavorite(movietvshow, state));
    }
}
