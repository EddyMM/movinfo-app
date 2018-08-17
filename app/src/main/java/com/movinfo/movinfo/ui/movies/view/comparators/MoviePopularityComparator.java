package com.movinfo.movinfo.ui.movies.view.comparators;

import com.movinfo.movinfo.data.network.models.Movie;

import java.util.Comparator;

/**
 * Comparator to compare movies by popularity
 */

public class MoviePopularityComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie movie, Movie otherMovie) {
        if (movie.getPopularity() > otherMovie.getPopularity()) {
            return 1;
        } else if(movie.getPopularity() < otherMovie.getPopularity()) {
            return -1;
        } else {
            return 0;
        }
    }
}
