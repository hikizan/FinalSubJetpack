package com.hikizan.myapplication.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {FavoriteMovie.class}, version = 1, exportSchema = false)
public abstract class FavoriteMovieRoomDatabase extends RoomDatabase {
    public abstract FavoriteMovieDao favoriteMovieDao();

    private static volatile FavoriteMovieRoomDatabase INSTANCE;

    public static FavoriteMovieRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FavoriteMovieRoomDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        FavoriteMovieRoomDatabase.class, "favoritemovie_database")
                        .build();
            }
        }
        return INSTANCE;
    }
}
