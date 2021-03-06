package com.agtechnosolution.entertainia.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.agtechnosolution.entertainia.R;
import com.agtechnosolution.entertainia.model.Genre;
import com.agtechnosolution.entertainia.model.Movie;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Created by AnujPc on 04-12-2018.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.customViewHolder>{

    private List<Movie> movies;
    private Context context;
    public static final String IMAGE_URL_BASE_PATH="http://image.tmdb.org/t/p/w342//";
    private List<Integer> genreIds;
    private List<Genre> genreItem;

    public MoviesAdapter(List<Movie> movies,List<Genre> genreItem,Context context) {
        this.movies = movies;
        this.context = context;
        this.genreItem=genreItem;
    }

    @Override
    public customViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.movie_item_layout,parent,false);
        return new customViewHolder(view);
    }

    @Override
    public void onBindViewHolder(customViewHolder holder, int position) {
        String image_url = IMAGE_URL_BASE_PATH + movies.get(position).getPosterPath();
        Picasso.with(context)
                .load(image_url)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(holder.movieImage);
        genreIds=movies.get(position).getGenreIds();
        if(genreIds==null || genreIds.isEmpty()){
            holder.genres.setText("N/A");
        }else{
            String nameMappedToId = "";
            for(int j=0;j<genreIds.size();j++) {
                for (int i = 0; i < genreItem.size(); i++) {
                    if (j<3 && genreItem.get(i).getId()==genreIds.get(j)) {
                        nameMappedToId +=genreItem.get(i).getGenreName()+" ";
                    }
                }
            }
            holder.genres.setText(nameMappedToId.trim());
        }
        holder.movieTitle.setText(movies.get(position).getTitle());
//        holder.movieDescription.setText(movies.get(position).getOverView()+"...");
        holder.date.setText("Release: "+movies.get(position).getReleaseDate());
        holder.rating.setText(String.valueOf(movies.get(position).getVoteAverage()));
        holder.tmdbRating.setText(String.valueOf(movies.get(position).getVoteCount()));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class customViewHolder extends RecyclerView.ViewHolder{

        ImageView movieImage;
        TextView movieTitle;
//        TextView movieDescription;
        TextView genres;
        TextView date;
        TextView rating,tmdbRating;

        public customViewHolder(View itemView) {
            super(itemView);
            movieImage=itemView.findViewById(R.id.movie_image);
            movieTitle=itemView.findViewById(R.id.title);
//            movieDescription=itemView.findViewById(R.id.description);
            genres=itemView.findViewById(R.id.genres);
            date=itemView.findViewById(R.id.date);
            rating=itemView.findViewById(R.id.rating);
            tmdbRating= itemView.findViewById(R.id.tmdb_rating);
        }
    }

}
