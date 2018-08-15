package com.movinfo.movinfo.data.network.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 */

public class MovieResponse {
    @SerializedName("poster_url")
    private String posterUrl;
    @SerializedName("vote_count")
    private int voteCount;
    @SerializedName("id")
    private String movieId;
    @SerializedName("vote_average")
    private float voteAverage;
    @SerializedName("title")
    private String title;
    @SerializedName("popularity")
    private float popularity;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("genre_ids")
    private ArrayList<String> genreIds;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("adult")
    private boolean adult;
    @SerializedName("overview")
    private String overview;
    @SerializedName("release_date")
    private Date releaseDate;

    public MovieResponse(String posterUrl, int voteCount, String movieId, float voteAverage,
            String title, float popularity, String posterPath, String originalLanguage,
            String originalTitle, ArrayList<String> genreIds, String backdropPath, boolean adult,
            String overview, Date releaseDate) {
        this.posterUrl = posterUrl;
        this.voteCount = voteCount;
        this.movieId = movieId;
        this.voteAverage = voteAverage;
        this.title = title;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.genreIds = genreIds;
        this.backdropPath = backdropPath;
        this.adult = adult;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "voteAverage=" + voteAverage +
                ", title='" + title + '\'' +
                '}';
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public String getMovieId() {
        return movieId;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public float getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public ArrayList<String> getGenreIds() {
        return genreIds;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }
}
