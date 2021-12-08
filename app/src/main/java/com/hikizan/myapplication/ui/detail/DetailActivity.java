package com.hikizan.myapplication.ui.detail;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hikizan.myapplication.model.source.local.entity.MovieTvshowEntity;
import com.hikizan.myapplication.R;
import com.hikizan.myapplication.viewmodel.DetailViewModel;
import com.hikizan.myapplication.viewmodel.ViewModelFactory;

public class DetailActivity extends AppCompatActivity {

    public static final String EX_MOVIES = "extra_movies";
    private TextView txtTitle;
    private TextView txtDateRelease;
    private TextView txtRating;
    private TextView txtUserScore;
    private TextView txtGenre;
    private TextView txtOverview;
    private TextView txtDuration;
    private ImageView img;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setComponent();

        ViewModelFactory factory = ViewModelFactory.getInstance(this);
        DetailViewModel detailViewModel = new ViewModelProvider(this, factory).get(DetailViewModel.class);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String moviesID = extras.getString(EX_MOVIES);
            if (moviesID != null) {
                char id = moviesID.charAt(0);

                //progressBar.setVisibility(View.VISIBLE);
                detailViewModel.setSelectedMovies(moviesID);
                if (id == 'm') {
                    detailViewModel.getDetailMovies("0").observe(this, movie -> {
                        /*
                        progressBar.setVisibility(View.GONE);
                        setData(movie);
                         */

                        if (movie != null){
                            switch (movie.status){
                                case LOADING:
                                    progressBar.setVisibility(View.VISIBLE);
                                    break;
                                case SUCCESS:
                                    progressBar.setVisibility(View.GONE);
                                    setData(movie.data);
                                    break;
                                case ERROR:
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    });
                } else {
                    detailViewModel.getDetailMovies("1").observe(this, movie -> {
                        /*
                        progressBar.setVisibility(View.GONE);
                        setData(movie.data);
                         */

                        if (movie != null){
                            switch (movie.status){
                                case LOADING:
                                    progressBar.setVisibility(View.VISIBLE);
                                    break;
                                case SUCCESS:
                                    progressBar.setVisibility(View.GONE);
                                    setData(movie.data);
                                    break;
                                case ERROR:
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    });

                }
            }
        }
    }

    private void setComponent() {
        progressBar = findViewById(R.id.progress_bar);
        txtTitle = findViewById(R.id.tv_detail_title);
        txtDateRelease = findViewById(R.id.tv_detail_dateRelease);
        txtRating = findViewById(R.id.tv_detail_rating);
        txtUserScore = findViewById(R.id.tv_detail_userScore);
        txtGenre = findViewById(R.id.tv_detail_genre);
        txtOverview = findViewById(R.id.tv_detail_overview);
        txtDuration = findViewById(R.id.tv_detail_duration);
        img = findViewById(R.id.tv_detail_image);
    }

    private void setData(MovieTvshowEntity movie) {
        txtTitle.setText(movie.getTitle());
        txtDateRelease.setText(movie.getDateRelease());
        txtRating.setText(movie.getRating());
        txtDuration.setText(movie.getDuration());
        txtUserScore.setText(getString(R.string.userScore, movie.getUserScore()));
        txtGenre.setText(movie.getGenre());
        txtOverview.setText(movie.getOverview());

        int resId = getResources().getIdentifier(movie.getImage(), "drawable", getPackageName());

        Glide.with(this)
                .load(resId)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh_24)
                        .error(R.drawable.ic_baseline_broken_image_24))
                .into(img);
    }
}