package com.hikizan.myapplication.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hikizan.myapplication.R;
import com.hikizan.myapplication.callback.FavoriteMovieClickCallback;
import com.hikizan.myapplication.database.FavoriteMovie;
import com.hikizan.myapplication.ui.detail.DetailActivity;

public class FavoritePagedListAdapter extends PagedListAdapter<FavoriteMovie, FavoritePagedListAdapter.FavoriteViewHolder> {
    private final FavoriteMovieClickCallback callback;
    private final Activity activity;

    public FavoritePagedListAdapter(Activity activity, FavoriteMovieClickCallback callback){
        super(DIFF_CALLBACK);
        this.activity = activity;
        this.callback = callback;
    }

    private static final DiffUtil.ItemCallback<FavoriteMovie> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<FavoriteMovie>() {
                @Override
                public boolean areItemsTheSame(@NonNull FavoriteMovie oldItem, @NonNull FavoriteMovie newItem) {
                    return oldItem.getIDMovieDB().equals(newItem.getIDMovieDB()) && oldItem.getTitle().equals(newItem.getTitle());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull FavoriteMovie oldItem, @NonNull FavoriteMovie newItem) {
                    return oldItem.equals(newItem);
                }
            };

    @NonNull
    @Override
    public FavoritePagedListAdapter.FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritePagedListAdapter.FavoriteViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class FavoriteViewHolder extends RecyclerView.ViewHolder {
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

        public void bind(FavoriteMovie favoriteMovie){
            tvTitle.setText(favoriteMovie.getTitle());
            tvDateRelease.setText(favoriteMovie.getDateRelease());
            tvOverview.setText(favoriteMovie.getOverview());
            share.setOnClickListener(view -> callback.onShareClick(favoriteMovie));

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.EX_MOVIES, favoriteMovie.getIDMovieDB());
                itemView.getContext().startActivity(intent);
            });

            int resId = itemView.getResources().getIdentifier(favoriteMovie.getImage(), "drawable", itemView.getContext().getPackageName());

            Glide.with(itemView.getContext())
                    .load(resId)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh_24).error(R.drawable.ic_baseline_broken_image_24))
                    .into(imgMovie);
        }
    }
}
