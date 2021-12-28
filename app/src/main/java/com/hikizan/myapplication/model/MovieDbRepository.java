package com.hikizan.myapplication.model;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

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
    public LiveData<Resource<PagedList<MovieTvshowEntity>>> getMovies(String checkId) {

        return new NetworkBoundResource<PagedList<MovieTvshowEntity>, List<MovieDbResponse>>(appExecutors){

            @Override
            protected LiveData<PagedList<MovieTvshowEntity>> loadFromDB() {
                PagedList.Config config = new PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build();

                if (checkId.equals("0")) {
                    return  new LivePagedListBuilder<>(localDataSource.getListMovies(), config).build();
                }else{
                    return new LivePagedListBuilder<>(localDataSource.getListTvShows(), config).build();
                }
            }

            @Override
            protected Boolean shouldFetch(PagedList<MovieTvshowEntity> data) {
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
    public LiveData<PagedList<MovieTvshowEntity>> getFavoritedMovies() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build();
        return new LivePagedListBuilder<>(localDataSource.getFavoritedMovies(), config).build();
    }

    @Override
    public LiveData<PagedList<MovieTvshowEntity>> getFavoritedTvshows() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build();
        return new LivePagedListBuilder<>(localDataSource.getFavoritedTvshows(), config).build();
    }

    @Override
    public void setMovieTvshowsFavorite(MovieTvshowEntity movietvshow, boolean state) {
        appExecutors.diskIO().execute(() -> localDataSource.setMovieTvshowFavorite(movietvshow, state));
    }
}
