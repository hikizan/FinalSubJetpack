package com.hikizan.myapplication.viewmodel;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.hikizan.myapplication.model.MovieDbRepository;
import com.hikizan.myapplication.model.source.local.entity.MovieTvshowEntity;
import com.hikizan.myapplication.utils.DummyData;
import com.hikizan.myapplication.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DetailViewModelTest {
    private DetailViewModel viewModel;
    private final MovieTvshowEntity dummyMovies = DummyData.generateDummyMovies().get(0);
    private final String movieId = dummyMovies.getIDMovieDB();

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

    }
}