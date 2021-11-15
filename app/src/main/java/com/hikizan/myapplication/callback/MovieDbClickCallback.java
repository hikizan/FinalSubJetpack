package com.hikizan.myapplication.callback;

import com.hikizan.myapplication.model.source.local.entity.MovieDbModel;

public interface MovieDbClickCallback {
    void onShareClick(MovieDbModel filmEntity);
}
