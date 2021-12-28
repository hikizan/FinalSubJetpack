package com.hikizan.myapplication.dinjection;

import android.content.Context;

import com.hikizan.myapplication.model.source.local.LocalDataSource;
import com.hikizan.myapplication.model.source.local.room.MovieRoomDatabase;
import com.hikizan.myapplication.utils.AppExecutors;
import com.hikizan.myapplication.utils.JsonHelper;
import com.hikizan.myapplication.model.MovieDbRepository;
import com.hikizan.myapplication.model.source.remote.RemoteDataSource;

public class Injection {
    public static MovieDbRepository provideRepository(Context context) {

        MovieRoomDatabase database = MovieRoomDatabase.getINSTANCE(context);

        RemoteDataSource remoteDataSource = RemoteDataSource.getInstance(new JsonHelper(context));
        LocalDataSource localDataSource = LocalDataSource.getINSTANCE(database.movieDao());
        AppExecutors appExecutors = new AppExecutors();

        return MovieDbRepository.getInstance(remoteDataSource, localDataSource, appExecutors);
    }
}

