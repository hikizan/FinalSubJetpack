package com.hikizan.myapplication.ui.favorite;

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

import com.hikizan.myapplication.R;
import com.hikizan.myapplication.adapter.FavoritePagedListAdapter;
import com.hikizan.myapplication.callback.FavoriteMovieClickCallback;
import com.hikizan.myapplication.database.FavoriteMovie;
import com.hikizan.myapplication.viewmodel.FavoriteViewModel;
import com.hikizan.myapplication.viewmodel.FavoriteViewModelFactory;

public class TvshowsFavoriteFragment extends Fragment implements FavoriteMovieClickCallback {

    private RecyclerView rvTvShowsFavorite;
    private ProgressBar progressBar;

    public TvshowsFavoriteFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tvshows_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTvShowsFavorite = view.findViewById(R.id.rv_tvshows_favorite);
        progressBar = view.findViewById(R.id.progress_bar);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null){

            FavoriteViewModelFactory mfactory = FavoriteViewModelFactory.getInstance(getActivity().getApplication());
            FavoriteViewModel viewModel = new ViewModelProvider(getActivity(), mfactory).get(FavoriteViewModel.class);

            FavoritePagedListAdapter favoritePagedListAdapter = new FavoritePagedListAdapter(getActivity(), this);

            progressBar.setVisibility(View.VISIBLE);
            viewModel.getAllTvShows().observe(getViewLifecycleOwner(), favoriteMovies -> {
                progressBar.setVisibility(View.GONE);
                favoritePagedListAdapter.submitList(favoriteMovies);
            });

            rvTvShowsFavorite.setLayoutManager(new LinearLayoutManager(getContext()));
            rvTvShowsFavorite.setHasFixedSize(true);
            rvTvShowsFavorite.setAdapter(favoritePagedListAdapter);
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