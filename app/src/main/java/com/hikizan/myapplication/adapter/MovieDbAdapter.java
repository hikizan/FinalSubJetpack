package com.hikizan.myapplication.adapter;

import android.annotation.SuppressLint;
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
import com.hikizan.myapplication.callback.MovieDbClickCallback;
import com.hikizan.myapplication.model.source.local.entity.MovieTvshowEntity;
import com.hikizan.myapplication.ui.detail.DetailActivity;

import java.util.ArrayList;
import java.util.List;

public class MovieDbAdapter extends PagedListAdapter<MovieTvshowEntity, MovieDbAdapter.MoviesViewHolder> {

    private final MovieDbClickCallback callback;
    private final ArrayList<MovieTvshowEntity> listMovies = new ArrayList<>();

    public MovieDbAdapter(MovieDbClickCallback callback) {
        super(DIFF_CALLBACK);
        this.callback = callback;
    }

    private static final DiffUtil.ItemCallback<MovieTvshowEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<MovieTvshowEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull MovieTvshowEntity oldItem, @NonNull MovieTvshowEntity newItem) {
                    return oldItem.getIDMovieDB().equals(newItem.getIDMovieDB());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull MovieTvshowEntity oldItem, @NonNull MovieTvshowEntity newItem) {
                    return oldItem.equals(newItem);
                }
            };

    public void setMovies(List<MovieTvshowEntity> movies) {
        if (movies == null) return;
        this.listMovies.clear();
        this.listMovies.addAll(movies);
    }

    @NonNull
    @Override
    public MovieDbAdapter.MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieDbAdapter.MoviesViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class MoviesViewHolder extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvDateRelease;
        final TextView tvOverview;
        final ImageButton share;
        final ImageView imgMovieDb;

        MoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvDateRelease = itemView.findViewById(R.id.tv_item_dateRelease);
            tvOverview = itemView.findViewById(R.id.tv_item_overview);
            share = itemView.findViewById(R.id.img_share);
            imgMovieDb = itemView.findViewById(R.id.img_movies);
        }

        public void bind(MovieTvshowEntity movieTvshowEntity) {
            tvTitle.setText(movieTvshowEntity.getTitle());
            tvDateRelease.setText(movieTvshowEntity.getDateRelease());
            tvOverview.setText(movieTvshowEntity.getOverview());
            share.setOnClickListener(v -> callback.onShareClick(movieTvshowEntity));

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.EX_MOVIES, movieTvshowEntity.getIDMovieDB());
                itemView.getContext().startActivity(intent);
            });

            int resId = itemView.getResources().getIdentifier(movieTvshowEntity.getImage(), "drawable", itemView.getContext().getPackageName());

            Glide.with(itemView.getContext())
                    .load(resId)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_refresh_24).error(R.drawable.ic_baseline_broken_image_24))
                    .into(imgMovieDb);
        }
    }
}
