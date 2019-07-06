package com.nikhildagrawal.flix_android.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nikhildagrawal.flix_android.models.Movie;

import java.util.List;

public class MovieSearchResponse {

    @SerializedName("total_results")
    @Expose()
    private String total_results;

    @SerializedName("total_pages")
    @Expose()
    private String total_pages;

    @SerializedName("results")
    @Expose()
    private List<Movie> movies;



    public String getTotal_results() {
        return total_results;
    }

    public String getTotal_pages() {
        return total_pages;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    @Override
    public String toString() {
        return "MovieSearchResponse{" +
                "total_results='" + total_results + '\'' +
                ", total_pages='" + total_pages + '\'' +
                ", movies=" + movies +
                '}';
    }
}
