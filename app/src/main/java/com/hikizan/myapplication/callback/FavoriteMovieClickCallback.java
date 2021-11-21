package com.hikizan.myapplication.callback;

import com.hikizan.myapplication.database.FavoriteMovie;

public interface FavoriteMovieClickCallback {
    void onShareClick(FavoriteMovie filmEntity);
}
