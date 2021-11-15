package com.hikizan.myapplication.model.source.remote;

import android.os.Handler;

import com.hikizan.myapplication.data.EspressoIdlingResource;
import com.hikizan.myapplication.data.JsonHelper;
import com.hikizan.myapplication.model.source.remote.response.MovieDbResponse;

import java.util.List;

public class RemoteDataSource {
    private static RemoteDataSource INSTANCE;
    private JsonHelper jsonHelper;
    private Handler handler = new Handler();
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

    public void getMovies(String checkId, LoadMoviesCallback callback) {
        EspressoIdlingResource.increment();
        handler.postDelayed(() -> {
            callback.onAllCoursesReceived(jsonHelper.loadDetailMovies(checkId));
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
    }

    public interface LoadMoviesCallback {
        void onAllCoursesReceived(List<MovieDbResponse> movieDbResponses);
    }
}


