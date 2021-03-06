package com.appsomniac.movie.tv.showcube.config.nearby;

import com.appsomniac.movie.tv.showcube.config.VideoResponse;
import com.appsomniac.movie.tv.showcube.config.movie.MovieResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by absolutelysaurabh on 6/10/17.
 */

public interface PlaceApiInterface {

    @GET("json")
    Call<PlaceApiResponse> getNearbyTheatres(@QueryMap Map<String, String> params);

}
