package com.hikizan.myapplication.callback;

import androidx.recyclerview.widget.DiffUtil;

import com.hikizan.myapplication.database.FavoriteMovie;

import java.util.List;

public class FavoriteDiffCallback extends DiffUtil.Callback {
    private final List<FavoriteMovie> mOldFavoriteMovieList;
    private final List<FavoriteMovie> mNewFavoriteMovieList;

    public FavoriteDiffCallback(List<FavoriteMovie> oldFavoriteMovieList, List<FavoriteMovie> newFavoriteMovieList) {
        this.mOldFavoriteMovieList = oldFavoriteMovieList;
        this.mNewFavoriteMovieList = newFavoriteMovieList;
    }

    @Override
    public int getOldListSize() {
        return mOldFavoriteMovieList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewFavoriteMovieList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldFavoriteMovieList.get(oldItemPosition).getId() == mNewFavoriteMovieList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final FavoriteMovie oldFilm = mOldFavoriteMovieList.get(oldItemPosition);
        final FavoriteMovie newFilm = mNewFavoriteMovieList.get(newItemPosition);

        return oldFilm.getIDMovieDB().equals(newFilm.getIDMovieDB()) && oldFilm.getTitle().equals(newFilm.getTitle());
    }
}
