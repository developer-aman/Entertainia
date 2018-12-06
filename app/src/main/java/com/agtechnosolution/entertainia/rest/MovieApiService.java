package com.agtechnosolution.entertainia.rest;

import com.agtechnosolution.entertainia.model.Genre;
import com.agtechnosolution.entertainia.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by AnujPc on 04-12-2018.
 */

public interface MovieApiService {

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("genre/movie/list")
    Call<Genre> getGenres(@Query("api_key") String apiKey);

    @GET("trending/movie/week")
    Call<MovieResponse> getTrendingMovies(@Query("api_key") String apiKey);
}
