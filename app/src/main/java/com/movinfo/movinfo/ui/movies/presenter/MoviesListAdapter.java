package com.movinfo.movinfo.ui.movies.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.movinfo.movinfo.BuildConfig;
import com.movinfo.movinfo.R;
import com.movinfo.movinfo.data.network.models.Movie;
import com.movinfo.movinfo.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class MoviesListAdapter extends
        RecyclerView.Adapter<MoviesListAdapter.MoviesListViewHolder> {
    private List<Movie> mMovies = new ArrayList<>();
    private Context mContext;

    public MoviesListAdapter(Context context, List<Movie> movies) {
        if(movies != null) {
            mMovies.addAll(movies);
        }
        mContext = context;
    }

    @NonNull
    @Override
    public MoviesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
            int viewType) {
        View movieListItemView = LayoutInflater.from(mContext).inflate(R.layout.movie_list_item,
                parent, false);
        return new MoviesListViewHolder(movieListItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesListViewHolder holder,
            int position) {
        holder.bind(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        int noOfMovies = 0;

        if (mMovies != null) {
            noOfMovies = mMovies.size();
        }

        return noOfMovies;
    }

    class MoviesListViewHolder extends RecyclerView.ViewHolder {

        MoviesListViewHolder(View itemView) {
            super(itemView);
        }

        void bind(Movie movie) {
            // Bind UI with data

            // Poster
            ImageView moviePosterImageView = itemView.findViewById(R.id.moviePosterImageView);
            String posterPath = Constants.MOVIE_DB_POSTER_URL + movie.getPosterPath()
                    + "?api_key=" + BuildConfig.TheMovieDbApiToken;
            Picasso.get().load(posterPath)
                    .placeholder(R.drawable.ic_image_black_24dp)
                    .error(R.drawable.ic_broken_image_black_24dp)
                    .into(moviePosterImageView);

            // Title
            TextView movieTitleTextView = itemView.findViewById(R.id.movieTitleTextView);
            movieTitleTextView.setText(movie.getTitle());

            // Rating
            RatingBar movieRatingBar = itemView.findViewById(R.id.movieRatingBar);
            float rating  = 5 * (movie.getVoteAverage() / 10);
            movieRatingBar.setRating(rating);

            // Vote counts
            TextView voteCountsTextView = itemView.findViewById(R.id.movieVoteCountsTextView);
            voteCountsTextView.setText(String.format("%s", movie.getVoteCount()));
        }
    }

    public void setMovies(List<Movie> movies) {
        mMovies.addAll(movies);
    }

    public List<Movie> getMovies() {
        return mMovies;
    }

    public void resetMoviesList() {
        mMovies.clear();
    }
}
