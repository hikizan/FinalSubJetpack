package com.hikizan.myapplication.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hikizan.myapplication.R;
import com.hikizan.myapplication.callback.FavoriteDiffCallback;
import com.hikizan.myapplication.callback.FavoriteMovieClickCallback;
import com.hikizan.myapplication.database.FavoriteMovie;
import com.hikizan.myapplication.ui.detail.DetailActivity;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {
    private final FavoriteMovieClickCallback callback;
    private final ArrayList<FavoriteMovie> listFavoriteMovie = new ArrayList<>();

    public FavoriteAdapter(FavoriteMovieClickCallback callback) {
        this.callback = callback;
    }

    void setListFavoriteMovie(List<FavoriteMovie> listFavoriteMovie) {
        final FavoriteDiffCallback diffCallback = new FavoriteDiffCallback(this.listFavoriteMovie, listFavoriteMovie);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.listFavoriteMovie.clear();
        this.listFavoriteMovie.addAll(listFavoriteMovie);
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public FavoriteAdapter.FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.FavoriteViewHolder holder, int position) {
        FavoriteMovie favoriteMovie = listFavoriteMovie.get(position);
        holder.tvTitle.setText(favoriteMovie.getTitle());
        holder.tvDateRelease.setText(favoriteMovie.getDateRelease());
        holder.tvOverview.setText(favoriteMovie.getOverview());
        holder.share.setOnClickListener(view -> callback.onShareClick(favoriteMovie));

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra(DetailActivity.EX_MOVIES, favoriteMovie.getIDMovieDB());
            holder.itemView.getContext().startActivity(intent);
        });

        int resId = holder.itemView.getResources().getIdentifier(favoriteMovie.getImage(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(resId)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh_24).error(R.drawable.ic_baseline_broken_image_24))
                .into(holder.imgMovie);

    }

    @Override
    public int getItemCount() {
        return listFavoriteMovie.size();
    }

    static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvDateRelease;
        final TextView tvOverview;
        final ImageButton share;
        final ImageView imgMovie;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvDateRelease = itemView.findViewById(R.id.tv_item_dateRelease);
            tvOverview = itemView.findViewById(R.id.tv_item_overview);
            share = itemView.findViewById(R.id.img_share);
            imgMovie = itemView.findViewById(R.id.img_movies);
        }
    }
}
