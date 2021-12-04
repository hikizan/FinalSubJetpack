package com.hikizan.myapplication.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.hikizan.myapplication.utils.DummyData;
import com.hikizan.myapplication.model.source.MovieDbRepository;
import com.hikizan.myapplication.model.source.local.entity.MovieDbModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TvShowsViewModelTest {
    private TvShowsViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private Observer<List<MovieDbModel>> observer;

    @Mock
    private MovieDbRepository movieDbRepository;

    @Before
    public void setUp() {
        viewModel = new TvShowsViewModel(movieDbRepository);
    }

    @Test
    public void getData() {

        ArrayList<MovieDbModel> dummyMovies = DummyData.generateDummyTvShows();
        MutableLiveData<List<MovieDbModel>> movies = new MutableLiveData<>();
        movies.setValue(dummyMovies);

        when(movieDbRepository.getMovies("1")).thenReturn(movies);
        List<MovieDbModel> ListMovies = viewModel.getData().getValue();
        verify(movieDbRepository).getMovies("1");
        assertNotNull(ListMovies);
        assertEquals(10, ListMovies.size());

        viewModel.getData().observeForever(observer);
        verify(observer).onChanged(dummyMovies);
    }
}