package com.nikhildagrawal.flix_android.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nikhildagrawal.flix_android.models.Movie;

import androidx.annotation.NonNull;

public class MovieDetailResponse {


    @SerializedName("id")  // this is name from response
    @Expose()  // So that GSON converter can serialize and Deserialize data.
    private String id;

    @SerializedName("title")
    @Expose()
    private String title;


    @SerializedName("overview")
    @Expose()
    private String overview;

    @SerializedName("poster_path")
    @Expose()
    private String poster_path;


    @SerializedName("release_date")
    @Expose()
    private String release_date;

    @SerializedName("vote_average")
    @Expose()
    private float vote_average;


    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public float getVote_average() {
        return vote_average;
    }

    public Movie getMovie(){


        return new Movie(getId(),getTitle(),getOverview(),getVote_average(),getPoster_path(),getRelease_date());
    }
}
