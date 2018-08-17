package com.movinfo.movinfo.ui.movies.view.comparators;

import com.movinfo.movinfo.data.network.models.Movie;

import java.util.Comparator;

/**
 * Comparator to compare movies by rating
 */

public class MovieRatingComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie movie, Movie otherMovie) {
        if (movie.getVoteAverage() > otherMovie.getVoteAverage()) {
            return 1;
        } else if(movie.getVoteAverage() < otherMovie.getVoteAverage()) {
            return -1;
        } else {
            return 0;
        }
    }
}
