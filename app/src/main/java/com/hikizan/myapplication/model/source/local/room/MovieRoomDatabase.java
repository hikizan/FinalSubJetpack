package com.hikizan.myapplication.model.source.local.room;

import android.content.Context;
import android.graphics.Movie;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.hikizan.myapplication.model.source.local.entity.MovieTvshowEntity;

@Database(entities = {MovieTvshowEntity.class},
        version = 1,
        exportSchema = false)
public abstract class MovieRoomDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();

    private static volatile MovieRoomDatabase INSTANCE;

    public static MovieRoomDatabase getINSTANCE(Context context) {
        if (INSTANCE == null){
            synchronized (MovieRoomDatabase.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        MovieRoomDatabase.class, "MovieRoom.db")
                        .build();
            }
        }
        return INSTANCE;
    }
}
