package com.hikizan.myapplication.ui.favorite;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.hikizan.myapplication.R;
import com.hikizan.myapplication.adapter.FavoriteAdapter;
import com.hikizan.myapplication.callback.FavoriteMovieClickCallback;
import com.hikizan.myapplication.database.FavoriteMovie;
import com.hikizan.myapplication.viewmodel.DetailFavoriteViewModel;
import com.hikizan.myapplication.viewmodel.FavoriteViewModel;
import com.hikizan.myapplication.viewmodel.FavoriteViewModelFactory;

public class TvshowsFavoriteFragment extends Fragment implements FavoriteMovieClickCallback {

    private RecyclerView rvTvShowsFavorite;
    private ProgressBar progressBar;
    private FavoriteViewModel favoriteViewModel;

    public TvshowsFavoriteFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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

            //favoriteViewModel = obtainFavoriteViewModel(getActivity().getApplication());


            FavoriteAdapter favoriteMovieAdapter = new FavoriteAdapter(this);

            progressBar.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void onShareClick(FavoriteMovie filmEntity) {

    }

    @NonNull
    private static FavoriteViewModel obtainFavoriteViewModel(AppCompatActivity activity) {
        FavoriteViewModelFactory mfactory = FavoriteViewModelFactory.getInstance(activity.getApplication());
        return new ViewModelProvider(activity, mfactory).get(FavoriteViewModel.class);
    }
}