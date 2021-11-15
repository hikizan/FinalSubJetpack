package com.hikizan.myapplication.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hikizan.myapplication.R;
import com.hikizan.myapplication.ui.favorite.MoviesFavoriteFragment;
import com.hikizan.myapplication.ui.favorite.TvshowsFavoriteFragment;

public class SectionsPagerFavoriteAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.movies, R.string.tvshows};
    private final Context mContext;

    public SectionsPagerFavoriteAdapter(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MoviesFavoriteFragment();
            case 1:
                return new TvshowsFavoriteFragment();
            default:
                return new Fragment();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position){
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() { return TAB_TITLES.length; }
}
