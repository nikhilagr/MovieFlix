package com.nikhildagrawal.flix_android.httprequests;

import com.nikhildagrawal.flix_android.util.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static MoviesApi moviesApi = retrofit.create(MoviesApi.class);

    public static   MoviesApi getMoviesApi(){
        return moviesApi;
    }


}
