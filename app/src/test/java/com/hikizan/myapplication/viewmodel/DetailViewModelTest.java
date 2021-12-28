package com.hikizan.myapplication.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.hikizan.myapplication.utils.DummyData;
import com.hikizan.myapplication.model.MovieDbRepository;
import com.hikizan.myapplication.model.source.local.entity.MovieTvshowEntity;
import com.hikizan.myapplication.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DetailViewModelTest {
    private DetailViewModel viewModel;
    private MovieTvshowEntity dummyMovies = DummyData.generateDummyMovies().get(0);
    private String movieId = dummyMovies.getIDMovieDB();

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private Observer<Resource<MovieTvshowEntity>> observer;

    @Mock
    private MovieDbRepository movieDbRepository;

    @Before
    public void setUp() {
        viewModel = new DetailViewModel(movieDbRepository);
        viewModel.setSelectedMovies(movieId);
    }

    @Test
    public void getDetailMovies() {
        Resource<MovieTvshowEntity> dummyMovieDetail = Resource.success(DummyData.generateDummyDetail(dummyMovies, true));
        MutableLiveData<Resource<MovieTvshowEntity>> movie = new MutableLiveData<>();
        movie.setValue(dummyMovieDetail);

        when(movieDbRepository.getDetailMovies("0", movieId)).thenReturn(movie);

        viewModel.getDetailMovies("0").observeForever(observer);

        verify(observer).onChanged(dummyMovieDetail);

        /*
        MutableLiveData<Resource<MovieTvshowEntity>> movies = new MutableLiveData<>();
        movies.setValue(dummyMovies);
        when(movieDbRepository.getDetailMovies("0", movieId)).thenReturn(movies);
        MovieTvshowEntity movieTvshowEntity = viewModel.getDetailMovies("0").getValue().data;
        verify(movieDbRepository).getDetailMovies("0", movieId);
        assertNotNull(movies);
        assertEquals(dummyMovies.getIDMovieDB(), movieTvshowEntity.getIDMovieDB());
        assertEquals(dummyMovies.getTitle(), movieTvshowEntity.getTitle());
        assertEquals(dummyMovies.getDateRelease(), movieTvshowEntity.getDateRelease());
        assertEquals(dummyMovies.getRating(), movieTvshowEntity.getRating());
        assertEquals(dummyMovies.getUserScore(), movieTvshowEntity.getUserScore());
        assertEquals(dummyMovies.getGenre(), movieTvshowEntity.getGenre());
        assertEquals(dummyMovies.getOverview(), movieTvshowEntity.getOverview());
        assertEquals(dummyMovies.getDuration(), movieTvshowEntity.getDuration());
        assertEquals(dummyMovies.getUrl(), movieTvshowEntity.getUrl());
        assertEquals(dummyMovies.getImage(), movieTvshowEntity.getImage());

        viewModel.getDetailMovies("0").observeForever(courseObserver);
        verify(courseObserver).onChanged(dummyMovies);
         */
    }
}