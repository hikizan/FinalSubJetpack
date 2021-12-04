package com.hikizan.myapplication.model.source.local;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.hikizan.myapplication.utils.DummyData;
import com.hikizan.myapplication.model.source.local.entity.MovieDbModel;
import com.hikizan.myapplication.model.source.remote.RemoteDataSource;
import com.hikizan.myapplication.model.source.remote.response.MovieDbResponse;
import com.hikizan.myapplication.utils.LiveDataTestUtil;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

public class MovieDbRepositoryTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private RemoteDataSource remote = Mockito.mock(RemoteDataSource.class);
    private FakeMovieDbRepository academyRepository = new FakeMovieDbRepository(remote);

    private ArrayList<MovieDbResponse> movieDbResponses = DummyData.generateRemoteDummyMovies();

    @Test
    public void getMovies() {
        doAnswer(invocation -> {
            ((RemoteDataSource.LoadMoviesCallback) invocation.getArguments()[1])
                    .onAllCoursesReceived(movieDbResponses);
            return null;
        }).when(remote).getMovies(eq("0"), any(RemoteDataSource.LoadMoviesCallback.class));
        List<MovieDbModel> movieDbModels = LiveDataTestUtil.getValue(academyRepository.getMovies("0"));
        verify(remote).getMovies(eq("0"), any(RemoteDataSource.LoadMoviesCallback.class));
        assertNotNull(movieDbModels);
        assertEquals(movieDbResponses.size(), movieDbModels.size());
    }


}