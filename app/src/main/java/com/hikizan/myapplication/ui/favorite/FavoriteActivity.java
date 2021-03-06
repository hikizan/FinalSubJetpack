package com.hikizan.myapplication.ui.favorite;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.hikizan.myapplication.R;
import com.hikizan.myapplication.adapter.SectionsPagerFavoriteAdapter;

public class FavoriteActivity extends AppCompatActivity {

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
    }

}