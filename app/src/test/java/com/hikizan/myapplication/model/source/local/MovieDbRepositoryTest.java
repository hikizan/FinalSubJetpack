package com.hikizan.myapplication.model.source.local;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;

import com.hikizan.myapplication.model.source.local.entity.MovieTvshowEntity;
import com.hikizan.myapplication.model.source.remote.RemoteDataSource;
import com.hikizan.myapplication.model.source.remote.response.MovieDbResponse;
import com.hikizan.myapplication.utils.AppExecutors;
import com.hikizan.myapplication.utils.DummyData;
import com.hikizan.myapplication.utils.LiveDataTestUtil;
import com.hikizan.myapplication.utils.PagedListUtil;
import com.hikizan.myapplication.vo.Resource;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class MovieDbRepositoryTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private final RemoteDataSource remote = Mockito.mock(RemoteDataSource.class);
    private final LocalDataSource local = mock(LocalDataSource.class);
    private final AppExecutors appExecutors = mock(AppExecutors.class);

    private final FakeMovieDbRepository academyRepository = new FakeMovieDbRepository(remote, local, appExecutors);

    private final ArrayList<MovieDbResponse> movieDbResponses = DummyData.generateRemoteDummyMovies();
    private final String movieDbId = movieDbResponses.get(0).getIDMovieDB();

    @Test
    public void getListMovies() {

        DataSource.Factory<Integer, MovieTvshowEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(local.getListMovies()).thenReturn(dataSourceFactory);
        academyRepository.getMovies("0");

        Resource<PagedList<MovieTvshowEntity>> moviesEntities = Resource.success(PagedListUtil.mockPagedList(DummyData.generateDummyMovies()));
        verify(local).getListMovies();
        assertNotNull(moviesEntities.data);
        assertEquals(movieDbResponses.size(), moviesEntities.data.size());
    }

    @Test
    public void getMovieDetail(){
        MutableLiveData<MovieTvshowEntity> dummyEntity = new MutableLiveData<>();
        dummyEntity.setValue(DummyData.generateDummyDetail(DummyData.generateDummyMovies().get(0), false));
        when(local.getMovieTvshowById(movieDbId)).thenReturn(dummyEntity);

        Resource<MovieTvshowEntity> moviesEntities = LiveDataTestUtil.getValue(academyRepository.getDetailMovies("0", movieDbId));
        verify(local).getMovieTvshowById(movieDbId);
        assertNotNull(moviesEntities.data);
        assertNotNull(moviesEntities.data.getTitle());
        assertEquals(movieDbResponses.get(0).getTitle(), moviesEntities.data.getTitle());
    }

    @Test
    public void getFavoritedMovies(){
        DataSource.Factory<Integer, MovieTvshowEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(local.getFavoritedMovies()).thenReturn(dataSourceFactory);
        academyRepository.getFavoritedMovies();

        Resource<PagedList<MovieTvshowEntity>> moviesEntities = Resource.success(PagedListUtil.mockPagedList(DummyData.generateDummyMovies()));
        verify(local).getFavoritedMovies();
        assertNotNull(moviesEntities);
        assertEquals(movieDbResponses.size(), moviesEntities.data.size());
    }

}