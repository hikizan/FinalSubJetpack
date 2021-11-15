package com.hikizan.myapplication.dinjection;

import android.content.Context;

import com.hikizan.myapplication.data.JsonHelper;
import com.hikizan.myapplication.model.source.local.MovieDbRepository;
import com.hikizan.myapplication.model.source.remote.RemoteDataSource;

public class Injection {
    public static MovieDbRepository provideRepository(Context context) {

        RemoteDataSource remoteDataSource = RemoteDataSource.getInstance(new JsonHelper(context));

        return MovieDbRepository.getInstance(remoteDataSource);
    }
}

