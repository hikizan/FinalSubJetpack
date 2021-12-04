package com.hikizan.myapplication.ui.movies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hikizan.myapplication.adapter.MovieDbAdapter;
import com.hikizan.myapplication.callback.MovieDbClickCallback;
import com.hikizan.myapplication.model.source.local.entity.MovieTvshowEntity;
import com.hikizan.myapplication.R;
import com.hikizan.myapplication.viewmodel.MoviesViewModel;
import com.hikizan.myapplication.viewmodel.ViewModelFactory;

public class MoviesFragment extends Fragment implements MovieDbClickCallback {

    private RecyclerView rvMovies;
    private ProgressBar progressBar;

    public MoviesFragment() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMovies = view.findViewById(R.id.rv_movies);
        progressBar = view.findViewById(R.id.progress_bar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            ViewModelFactory factory = ViewModelFactory.getInstance(getActivity());
            MoviesViewModel viewModel = new ViewModelProvider(this, factory).get(MoviesViewModel.class);

            MovieDbAdapter moviesAdapter = new MovieDbAdapter(this);
            progressBar.setVisibility(View.VISIBLE);
            viewModel.getData().observe(getViewLifecycleOwner(), movies -> {
                        progressBar.setVisibility(View.GONE);
                        moviesAdapter.setMovies(movies);
                        moviesAdapter.notifyDataSetChanged();
                    }
            );

            rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
            rvMovies.setHasFixedSize(true);
            rvMovies.setAdapter(moviesAdapter);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onShareClick(MovieTvshowEntity movie) {
        if (getActivity() != null) {
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(getActivity())
                    .setType(mimeType)
                    .setChooserTitle(R.string.title_share)
                    .setText(getString(R.string.share, movie.getUrl()))
                    .startChooser();
        }
    }
}