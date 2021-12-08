package com.hikizan.myapplication.model.source.remote;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hikizan.myapplication.utils.EspressoIdlingResource;
import com.hikizan.myapplication.utils.JsonHelper;
import com.hikizan.myapplication.model.source.remote.response.MovieDbResponse;

import java.util.List;

public class RemoteDataSource {
    private static RemoteDataSource INSTANCE;
    private JsonHelper jsonHelper;
    private Handler handler = new Handler(Looper.getMainLooper());
    private final long SERVICE_LATENCY_IN_MILLIS = 2000;

    private RemoteDataSource(JsonHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }

    public static RemoteDataSource getInstance(JsonHelper helper) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource(helper);
        }
        return INSTANCE;
    }

    public LiveData<ApiResponse<List<MovieDbResponse>>> getMovies(String checkId) {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<MovieDbResponse>>> resultMovies = new MutableLiveData<>();
        handler.postDelayed(() -> {
            resultMovies.setValue(ApiResponse.success(jsonHelper.loadDetailMovies(checkId)));
            //callback.onAllCoursesReceived(jsonHelper.loadDetailMovies(checkId));
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
        return resultMovies;
    }

    public LiveData<ApiResponse<MovieDbResponse>> getDetailMovie(String checkId, String IDMovieDB){
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<MovieDbResponse>> resultDetailMovie = new MutableLiveData<>();
        handler.postDelayed(() -> {
            resultDetailMovie.setValue(ApiResponse.success(jsonHelper.loadDetailContent(checkId, IDMovieDB)));
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
        return resultDetailMovie;
    }

    public interface LoadMoviesCallback {
        void onAllCoursesReceived(List<MovieDbResponse> movieDbResponses);
    }
}


