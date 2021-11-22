package com.hikizan.myapplication.ui.favorite;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.hikizan.myapplication.R;
import com.hikizan.myapplication.adapter.FavoriteAdapter;
import com.hikizan.myapplication.callback.FavoriteMovieClickCallback;
import com.hikizan.myapplication.database.FavoriteMovie;
import com.hikizan.myapplication.viewmodel.FavoriteViewModel;
import com.hikizan.myapplication.viewmodel.FavoriteViewModelFactory;

public class MoviesFavoriteFragment extends Fragment implements FavoriteMovieClickCallback {

    private RecyclerView rvMoviesFavorite;
    private ProgressBar progressBar;
    private FavoriteViewModel favoriteViewModel;

    public MoviesFavoriteFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMoviesFavorite = view.findViewById(R.id.rv_movies_favorite);
        progressBar = view.findViewById(R.id.progress_bar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null){
            FavoriteViewModelFactory mfactory = FavoriteViewModelFactory.getInstance(getActivity().getApplication());
            FavoriteViewModel viewModel = new ViewModelProvider(getActivity(), mfactory).get(FavoriteViewModel.class);

            FavoriteAdapter favoriteMoviesAdapter = new FavoriteAdapter(this);

            progressBar.setVisibility(View.VISIBLE);
            viewModel.getAllMovies().observe(getViewLifecycleOwner(), favoriteMovies -> {
                progressBar.setVisibility(View.GONE);
                favoriteMoviesAdapter.setListFavoriteMovie(favoriteMovies);
                favoriteMoviesAdapter.notifyDataSetChanged();
            });

            rvMoviesFavorite.setLayoutManager(new LinearLayoutManager(getContext()));
            rvMoviesFavorite.setHasFixedSize(true);
            rvMoviesFavorite.setAdapter(favoriteMoviesAdapter);
        }
    }

    @Override
    public void onShareClick(FavoriteMovie filmEntity) {
        if (getActivity() != null){
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(getActivity())
                    .setType(mimeType)
                    .setChooserTitle(R.string.title_share)
                    .setText(getString(R.string.share, filmEntity.getUrl()))
                    .startChooser();
        }
    }
}