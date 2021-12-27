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
import android.widget.Toast;

import com.hikizan.myapplication.R;
import com.hikizan.myapplication.adapter.MovieDbAdapter;
import com.hikizan.myapplication.callback.MovieDbClickCallback;
import com.hikizan.myapplication.model.source.local.entity.MovieTvshowEntity;
import com.hikizan.myapplication.viewmodel.FavoriteViewModel;
import com.hikizan.myapplication.viewmodel.ViewModelFactory;

public class TvshowsFavoriteFragment extends Fragment implements MovieDbClickCallback {

    private RecyclerView rvTvshowsFavorite;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshows_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTvshowsFavorite = view.findViewById(R.id.rv_tvshows_favorite);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ViewModelFactory factory = ViewModelFactory.getInstance(getActivity());
        FavoriteViewModel viewModel = new ViewModelProvider(this, factory).get(FavoriteViewModel.class);

        MovieDbAdapter adapter = new MovieDbAdapter(this);

        viewModel.getFavoriteTvShowList().observe(getViewLifecycleOwner(), tvShows -> {
            if (tvShows.isEmpty()){
                Toast.makeText(getContext(), "Tidak ada TvShow favorite", Toast.LENGTH_SHORT).show();
            }else{
                adapter.setMovies(tvShows);
                rvTvshowsFavorite.setLayoutManager(new LinearLayoutManager(getContext()));
                rvTvshowsFavorite.setHasFixedSize(true);
                rvTvshowsFavorite.setAdapter(adapter);
            }
        });


    }

    @Override
    public void onShareClick(MovieTvshowEntity filmEntity) {
        if (getActivity() != null) {
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