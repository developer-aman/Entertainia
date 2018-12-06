package com.agtechnosolution.entertainia.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.agtechnosolution.entertainia.R;
import com.agtechnosolution.entertainia.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by AnujPc on 06-12-2018.
 */

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.customViewHolderTrending>{

    private List<Movie> trendingMovies;
    private Context context;
    public static final String IMAGE_URL_BASE_PATH="http://image.tmdb.org/t/p/w500//";

    public TrendingAdapter(List<Movie> trendingMovies, Context context) {
        this.trendingMovies = trendingMovies;
        this.context = context;
    }

    @Override
    public customViewHolderTrending onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.recycler_item_trending_movie,parent,false);
        return new customViewHolderTrending(view);

    }

    @Override
    public void onBindViewHolder(customViewHolderTrending holder, int position) {
        String image_url = IMAGE_URL_BASE_PATH + trendingMovies.get(position).getPosterPath();
        Picasso.with(context)
                .load(image_url)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(holder.trendingMovieImage);

        holder.releaseDate.setText("Release: "+trendingMovies.get(position).getReleaseDate());
    }

    @Override
    public int getItemCount() {
        return trendingMovies.size();
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class customViewHolderTrending extends RecyclerView.ViewHolder{

        ImageView trendingMovieImage;
        TextView releaseDate;

        public customViewHolderTrending(View itemView) {
            super(itemView);
            trendingMovieImage=itemView.findViewById(R.id.movie_image);
            releaseDate=itemView.findViewById(R.id.date);
        }
    }
}
