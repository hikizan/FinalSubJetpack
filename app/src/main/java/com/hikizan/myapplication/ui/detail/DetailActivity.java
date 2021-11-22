package com.hikizan.myapplication.ui.detail;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hikizan.myapplication.database.FavoriteMovie;
import com.hikizan.myapplication.model.source.local.entity.MovieDbModel;
import com.hikizan.myapplication.R;
import com.hikizan.myapplication.viewmodel.DetailFavoriteViewModel;
import com.hikizan.myapplication.viewmodel.DetailViewModel;
import com.hikizan.myapplication.viewmodel.FavoriteViewModelFactory;
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
    private FloatingActionButton fabFavorite;

    private DetailFavoriteViewModel detailFavoriteViewModel;
    private Boolean isFavorite = false;
    private FavoriteMovie tempFavoriteMovie = null;
    private FavoriteMovie fromMovieDb = null;
    private FavoriteMovie fromFavoriteMovie = null;

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
        detailFavoriteViewModel = obtainFavoriteViewModel(this);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String moviesID = extras.getString(EX_MOVIES);
            if (moviesID != null) {
                char id = moviesID.charAt(0);

                progressBar.setVisibility(View.VISIBLE);
                detailViewModel.setSelectedMovies(moviesID);
                if (id == 'm') {
                    detailViewModel.getDetailMovies("0").observe(this, movie -> {
                        progressBar.setVisibility(View.GONE);
                        setData(movie);


                        Log.d("DetailActivity", "onCreate: movieID = m; fromMovieDb = "+fromMovieDb);
                    });
                } else {
                    detailViewModel.getDetailMovies("1").observe(this, movie -> {
                        progressBar.setVisibility(View.GONE);
                        setData(movie);


                    });

                }

            }
        }



        fabFavorite.setOnClickListener(view -> {
            if (view.getId() == R.id.fab_favorite){
                if (isFavorite){
                    if (fromMovieDb != null){
                        detailFavoriteViewModel.dataToDelete(tempFavoriteMovie.getIDMovieDB());
                    }else{
                        detailFavoriteViewModel.delete(tempFavoriteMovie);
                    }
                }else{
                    detailFavoriteViewModel.insert(tempFavoriteMovie);
                }
            }
        });
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
        fabFavorite = findViewById(R.id.fab_favorite);
    }

    private void setData(MovieDbModel movie) {
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

        fromMovieDb = new FavoriteMovie();
        fromMovieDb.setIDMovieDB(movie.getIDMovieDB());
        fromMovieDb.setTitle(movie.getTitle());
        fromMovieDb.setDateRelease(movie.getDateRelease());
        fromMovieDb.setRating(movie.getRating());
        fromMovieDb.setUserScore(movie.getUserScore());
        fromMovieDb.setGenre(movie.getGenre());
        fromMovieDb.setOverview(movie.getOverview());
        fromMovieDb.setDuration(movie.getDuration());
        fromMovieDb.setUrl(movie.getUrl());
        fromMovieDb.setImage(movie.getImage());

        checkFavoriteMovie(fromMovieDb);
    }

    @NonNull
    private static DetailFavoriteViewModel obtainFavoriteViewModel(AppCompatActivity activity) {
        FavoriteViewModelFactory mfactory = FavoriteViewModelFactory.getInstance(activity.getApplication());
        return new ViewModelProvider(activity, mfactory).get(DetailFavoriteViewModel.class);
    }

    private void checkFavoriteMovie(FavoriteMovie fromMovieDb){
        Log.d("DetailActivity", "checkFavoriteMovie: fromMovieDb = "+fromMovieDb);
        if (fromMovieDb != null){
            tempFavoriteMovie = fromMovieDb;
            Log.d("DetailActivity", "checkFavoriteMovie on if branch: tempFavoriteMovie.IDMovieDB = "+tempFavoriteMovie.getIDMovieDB());

            detailFavoriteViewModel.findSpecificFilm(tempFavoriteMovie.getIDMovieDB()).observe(this, findMovieOnFavorite -> {
                Log.d("DetailActivity", "checkFavoriteMovie: isFavorite? getFromFavoriteDB = "+findMovieOnFavorite);
                if (findMovieOnFavorite != null){
                    isFavorite = true;
                    setFabFav(true);
                }else if (findMovieOnFavorite == null){
                    isFavorite = false;
                    setFabFav(false);
                }
            });
        }

    }

    private void setFabFav(Boolean isFav) {
        Drawable fabIsFav = ContextCompat.getDrawable(this, R.drawable.ic_addfavorite);
        Drawable fabNoFav = ContextCompat.getDrawable(this, R.drawable.ic_unfavorite);

        if (isFav) {
            fabFavorite.setImageDrawable(fabIsFav);
        } else {
            fabFavorite.setImageDrawable(fabNoFav);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}