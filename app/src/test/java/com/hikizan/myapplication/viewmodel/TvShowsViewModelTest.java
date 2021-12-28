package com.hikizan.myapplication.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

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
    private Observer<Resource<PagedList<MovieTvshowEntity>>> observer;

    @Mock
    private MovieDbRepository movieDbRepository;

    @Mock
    private PagedList<MovieTvshowEntity> pagedList;

    @Before
    public void setUp() {
        viewModel = new TvShowsViewModel(movieDbRepository);
    }

    @Test
    public void getData() {

        Resource<PagedList<MovieTvshowEntity>> dummyMovies = Resource.success(pagedList);
        when(dummyMovies.data.size()).thenReturn(10);
        MutableLiveData<Resource<PagedList<MovieTvshowEntity>>> movies = new MutableLiveData<>();
        movies.setValue(dummyMovies);

        when(movieDbRepository.getMovies("1")).thenReturn(movies);
        List<MovieTvshowEntity> ListMovies = viewModel.getData().getValue().data;
        verify(movieDbRepository).getMovies("1");
        assertNotNull(ListMovies);
        assertEquals(10, ListMovies.size());

        viewModel.getData().observeForever(observer);
        verify(observer).onChanged(dummyMovies);
    }
}