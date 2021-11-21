package com.hikizan.myapplication.ui.favorite;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.hikizan.myapplication.R;
import com.hikizan.myapplication.adapter.SectionsPagerFavoriteAdapter;
import com.hikizan.myapplication.viewmodel.FavoriteViewModel;
import com.hikizan.myapplication.viewmodel.FavoriteViewModelFactory;

public class FavoriteActivity extends AppCompatActivity {
    public FavoriteViewModel favoriteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        SectionsPagerFavoriteAdapter sectionsPagerFavoriteAdapter = new SectionsPagerFavoriteAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerFavoriteAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        if (getSupportActionBar() != null){
            getSupportActionBar().setElevation(0);
        }

        favoriteViewModel = obtainFavoriteViewModel(this);
    }

    @NonNull
    private static FavoriteViewModel obtainFavoriteViewModel(AppCompatActivity activity) {
        FavoriteViewModelFactory mfactory = FavoriteViewModelFactory.getInstance(activity.getApplication());
        return new ViewModelProvider(activity, mfactory).get(FavoriteViewModel.class);
    }

}