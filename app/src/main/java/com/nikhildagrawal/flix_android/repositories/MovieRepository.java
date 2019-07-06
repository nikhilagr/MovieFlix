package com.nikhildagrawal.flix_android.repositories;

import com.nikhildagrawal.flix_android.httprequests.MoviesApiClient;
import com.nikhildagrawal.flix_android.models.Movie;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MovieRepository {

    private static MovieRepository instance;

    private MoviesApiClient moviesApiClient;

    private LiveData<List<Movie>> mMoviesList;



    public static MovieRepository getInstance(){

        if(instance == null){
            instance = new MovieRepository();
        }

        return instance;
    }

    private MovieRepository(){
        moviesApiClient = MoviesApiClient.getInstance();

    }

    public LiveData<List<Movie>> getMoviesList(){
        return moviesApiClient.getMovies();
    }

    public void searchMoviesApi(String query, int page){
        if(page <= 0){
            page = 1;
        }

        moviesApiClient.searchMoviesApi(query,page);
    }



}
