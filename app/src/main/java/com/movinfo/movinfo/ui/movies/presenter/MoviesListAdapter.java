package com.movinfo.movinfo.ui.movies.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.movinfo.movinfo.BuildConfig;
import com.movinfo.movinfo.R;
import com.movinfo.movinfo.data.network.models.Movie;
import com.movinfo.movinfo.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

import timber.log.Timber;

/**
 *
 */

public class MoviesListAdapter extends
        RecyclerView.Adapter<MoviesListAdapter.MoviesListViewHolder> {
    private List<Movie> mMovies;
    private Context mContext;

    public MoviesListAdapter(Context context, List<Movie> movies) {
        mMovies = movies;
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
        return mMovies.size();
    }

    class MoviesListViewHolder extends RecyclerView.ViewHolder {

        MoviesListViewHolder(View itemView) {
            super(itemView);
        }

        void bind(Movie movie) {
            // Bind UI with data
            ImageView moviePosterImageView = itemView.findViewById(R.id.moviePosterImageView);
            String posterPath = Constants.MOVIE_DB_POSTER_URL + movie.getPosterPath()
                    + "?api_key=" + BuildConfig.TheMovieDbApiToken;
            Timber.d("Poster path: " + posterPath);
            Picasso.get().load(posterPath)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(moviePosterImageView);

            TextView movieTitleTextView = itemView.findViewById(R.id.movieTitleTextView);
            movieTitleTextView.setText(movie.getTitle());
        }
    }
}
