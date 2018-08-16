package com.movinfo.movinfo.data.network.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 */

public class PopularMoviesResponse {
    @SerializedName("page")
    private
    String page;
    @SerializedName("total_results")
    private
    String totalResults;
    @SerializedName("total_pages")
    private
    String totalPages;
    @SerializedName("results")
    private
    List<Movie> results;

    public PopularMoviesResponse(String page, String totalResults, String totalPages,
            List<Movie> results) {
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
        this.results = results;
    }

    public String getPage() {
        return page;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public List<Movie> getResults() {
        return results;
    }
}
