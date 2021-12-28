package com.hikizan.myapplication.ui.tvShows;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

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
import com.hikizan.myapplication.viewmodel.TvShowsViewModel;
import com.hikizan.myapplication.viewmodel.ViewModelFactory;

public class TvshowsFragment extends Fragment implements MovieDbClickCallback {

    private ProgressBar progressBar;

    public TvshowsFragment() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rvTvShows = view.findViewById(R.id.rv_tvshows);
        progressBar = view.findViewById(R.id.progress_bar);

        if (getActivity() != null) {
            ViewModelFactory factory = ViewModelFactory.getInstance(getActivity());
            TvShowsViewModel viewModel = new ViewModelProvider(this, factory).get(TvShowsViewModel.class);

            MovieDbAdapter moviesAdapter = new MovieDbAdapter(this);


            viewModel.getData().observe(getViewLifecycleOwner(), movies -> {
                if (movies != null) {
                    switch (movies.status) {
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            progressBar.setVisibility(View.GONE);
                            moviesAdapter.submitList(movies.data);
                            moviesAdapter.notifyDataSetChanged();
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });

            rvTvShows.setLayoutManager(new LinearLayoutManager(getContext()));
            rvTvShows.setHasFixedSize(true);
            rvTvShows.setAdapter(moviesAdapter);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tvshows, container, false);

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