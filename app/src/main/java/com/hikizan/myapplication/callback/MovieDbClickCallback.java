package com.hikizan.myapplication.callback;

import com.hikizan.myapplication.model.source.local.entity.MovieTvshowEntity;

public interface MovieDbClickCallback {
    void onShareClick(MovieTvshowEntity filmEntity);
}
