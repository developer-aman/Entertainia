package com.agtechnosolution.entertainia.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.agtechnosolution.entertainia.R;
import com.agtechnosolution.entertainia.adapter.MoviesAdapter;
import com.agtechnosolution.entertainia.adapter.TrendingAdapter;
import com.agtechnosolution.entertainia.model.Genre;
import com.agtechnosolution.entertainia.model.Movie;
import com.agtechnosolution.entertainia.model.MovieResponse;
import com.agtechnosolution.entertainia.rest.MovieApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView moviesRecycler,trendingRecycler;
    private static final String TAG=MainActivity.class.getSimpleName();
    public static final String BASE_URL="http://api.themoviedb.org/3/";
    private static Retrofit retrofit=null;
    private static final String API_KEY="ac6bdfe572bd6fea012ee95c4372a855";
    private List<Genre> genresList;
    MovieApiService movieApiService;
    private boolean once=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        trendingRecycler=findViewById(R.id.trending_recycler);
        trendingRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        moviesRecycler=findViewById(R.id.movies_recycler);
        moviesRecycler.setHasFixedSize(true);
        moviesRecycler.setLayoutManager(new LinearLayoutManager(this));
        connectApi();
        if(once){
            getGenresList();
        }
        getApiData();
    }

    public void connectApi(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        movieApiService=retrofit.create(MovieApiService.class);
    }

    public void getGenresList(){
        movieApiService.getGenres(API_KEY).enqueue(new Callback<Genre>() {
            @Override
            public void onResponse(Call<Genre> call, Response<Genre> response) {
                genresList=response.body().genresBundle;
                once=false;
            }

            @Override
            public void onFailure(Call<Genre> call, Throwable t) {
                Log.e(TAG,t.toString());
            }
        });
    }

    public void getApiData(){

        Call<MovieResponse> call=movieApiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> moviesList=response.body().getResults();
                moviesRecycler.setAdapter(new MoviesAdapter(moviesList,genresList,MainActivity.this));
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG,t.toString());
            }
        });

        Call<MovieResponse> call2=movieApiService.getTrendingMovies(API_KEY);
        call2.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> trendingMoviesList=response.body().getResults();
                trendingRecycler.setAdapter(new TrendingAdapter(trendingMoviesList,MainActivity.this));
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG,t.toString());
            }
        });
    }

}
