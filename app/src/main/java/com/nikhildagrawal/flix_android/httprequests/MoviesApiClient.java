package com.nikhildagrawal.flix_android.httprequests;

import android.util.Log;

import com.nikhildagrawal.flix_android.AppExecutors;
import com.nikhildagrawal.flix_android.models.Movie;
import com.nikhildagrawal.flix_android.responses.MovieSearchResponse;
import com.nikhildagrawal.flix_android.util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Response;

public class MoviesApiClient {

    private static  MoviesApiClient instance;
    private MutableLiveData<List<Movie>> mMoviesList;
    private static final String TAG = "MoviesApiClient";
    private SearchMoviesRunnable mSearchMoviesRunnable;
    
    
    
    public static MoviesApiClient getInstance(){
        if(instance == null){
            instance = new MoviesApiClient();
        }

        return instance;
    }

    private MoviesApiClient(){
        mMoviesList = new MutableLiveData<>();
    }


    public LiveData<List<Movie>> getMovies(){
        return mMoviesList;
    }


    public void searchMoviesApi(String query,int page){


        //AS EACH TIME WE WANT TO PASS NEW INSTANCE
        if(mSearchMoviesRunnable != null){
            mSearchMoviesRunnable = null;
        }

        mSearchMoviesRunnable = new SearchMoviesRunnable(query,page);



        final Future handler = AppExecutors.getInstance().networkIO().submit(mSearchMoviesRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {

                //TODO: Inform Users
                handler.cancel(true);
            }
        },3000, TimeUnit.MILLISECONDS);
    }


    private class SearchMoviesRunnable implements Runnable{



        String query;
        int page;
        boolean cancleRequest;

        public SearchMoviesRunnable(String query, int page) {
            this.query = query;
            this.page = page;
            this.cancleRequest = false;
        }

        @Override
        public void run() {



            try{
                Response response = getMovies(this.query,this.page).execute();

                if(cancleRequest){
                    return;
                }


                if(response.code() == 200){

                    List<Movie> list = new ArrayList<Movie>(((MovieSearchResponse)response.body()).getMovies());
                    if(page == 1){
                        mMoviesList.postValue(list);
                    }else{
                        List<Movie> moreMovies = mMoviesList.getValue();

                        moreMovies.addAll(list);
                        mMoviesList.postValue(moreMovies);
                    }

                }else{
                    String error = response.errorBody().string();

                    Log.e(TAG,error);
                    mMoviesList.postValue(null);
                }


            }catch (IOException e){
                e.printStackTrace();
                mMoviesList.postValue(null);
            }
        }


        private Call<MovieSearchResponse> getMovies(String query,int page ){

            return ServiceGenerator.getMoviesApi().searchMovies(Constants.API_KEY,query,String.valueOf(page));
        }


        private void cancleRequest(){
            Log.d(TAG,"cancle request : Cancelling....");
            cancleRequest = true;
        }
    }



}
