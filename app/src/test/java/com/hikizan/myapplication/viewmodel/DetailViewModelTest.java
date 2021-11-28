package com.hikizan.myapplication.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.hikizan.myapplication.data.DummyData;
import com.hikizan.myapplication.model.source.MovieDbRepository;
import com.hikizan.myapplication.model.source.local.entity.MovieDbModel;

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
    private MovieDbModel dummyMovies = DummyData.generateDummyMovies().get(0);
    private String movieId = dummyMovies.getIDMovieDB();

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private Observer<MovieDbModel> courseObserver;

    @Mock
    private MovieDbRepository movieDbRepository;

    @Before
    public void setUp() {
        viewModel = new DetailViewModel(movieDbRepository);
        viewModel.setSelectedMovies(movieId);
    }

    @Test
    public void getDetailMovies() {
        MutableLiveData<MovieDbModel> movies = new MutableLiveData<>();
        movies.setValue(dummyMovies);
        when(movieDbRepository.getDetailMovies("0", movieId)).thenReturn(movies);
        MovieDbModel movieDbModel = viewModel.getDetailMovies("0").getValue();
        verify(movieDbRepository).getDetailMovies("0", movieId);
        assertNotNull(movies);
        assertEquals(dummyMovies.getIDMovieDB(), movieDbModel.getIDMovieDB());
        assertEquals(dummyMovies.getTitle(), movieDbModel.getTitle());
        assertEquals(dummyMovies.getDateRelease(), movieDbModel.getDateRelease());
        assertEquals(dummyMovies.getRating(), movieDbModel.getRating());
        assertEquals(dummyMovies.getUserScore(), movieDbModel.getUserScore());
        assertEquals(dummyMovies.getGenre(), movieDbModel.getGenre());
        assertEquals(dummyMovies.getOverview(), movieDbModel.getOverview());
        assertEquals(dummyMovies.getDuration(), movieDbModel.getDuration());
        assertEquals(dummyMovies.getUrl(), movieDbModel.getUrl());
        assertEquals(dummyMovies.getImage(), movieDbModel.getImage());

        viewModel.getDetailMovies("0").observeForever(courseObserver);
        verify(courseObserver).onChanged(dummyMovies);
    }
}