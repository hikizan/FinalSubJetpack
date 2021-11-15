package com.hikizan.myapplication.data;

import android.content.Context;

import com.hikizan.myapplication.model.source.remote.response.MovieDbResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonHelper {
    private Context context;

    public JsonHelper(Context context) {
        this.context = context;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private String parsingFileToString(String fileName) {
        try {
            InputStream is = context.getAssets().open(fileName);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new String(buffer);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<MovieDbResponse> loadDetailMovies(String checkId) {
        ArrayList<MovieDbResponse> list = new ArrayList<>();
        try {
            String json = parsingFileToString("DbMovieResponses.json");
            if (json != null) {
                JSONObject responseObject = new JSONObject(json);
                JSONArray listArray;

                if (checkId.equals("0")) {
                    listArray = responseObject.getJSONArray("movies");
                } else {
                    listArray = responseObject.getJSONArray("tvShows");
                }

                for (int i = 0; i < listArray.length(); i++) {
                    JSONObject tvShow = listArray.getJSONObject(i);

                    String IDMovieDB = tvShow.getString("IDMovieDB");
                    String title = tvShow.getString("title");
                    String dateRelease = tvShow.getString("dateRelease");
                    String rating = tvShow.getString("rating");
                    String userScore = tvShow.getString("userScore");
                    String genre = tvShow.getString("genre");
                    String overview = tvShow.getString("overview");
                    String duration = tvShow.getString("duration");
                    String url = tvShow.getString("url");
                    String image = tvShow.getString("image");

                    MovieDbResponse movieDbResponse = new MovieDbResponse(IDMovieDB, title, dateRelease, rating, userScore, genre, overview, duration, url, image);
                    list.add(movieDbResponse);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
