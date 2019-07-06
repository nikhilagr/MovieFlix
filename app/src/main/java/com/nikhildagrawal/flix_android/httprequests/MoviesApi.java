package com.nikhildagrawal.flix_android.httprequests;

import com.nikhildagrawal.flix_android.models.Movie;
import com.nikhildagrawal.flix_android.responses.MovieDetailResponse;
import com.nikhildagrawal.flix_android.responses.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesApi {

    @GET("search/movie")
    Call<MovieSearchResponse> searchMovies(
        @Query("api_key") String key,
        @Query("query") String query,
        @Query("page") String page

    );


    @GET("movie/{movie_id}")
    Call<MovieDetailResponse> getMovieDetail(
            @Path(value="movie_id",encoded = true) String movieId,
            @Query("api_key") String key
    );




}
