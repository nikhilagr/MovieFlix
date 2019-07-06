package com.nikhildagrawal.flix_android;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.nikhildagrawal.flix_android.adapters.MovieListAdapter;
import com.nikhildagrawal.flix_android.httprequests.MoviesApi;
import com.nikhildagrawal.flix_android.httprequests.ServiceGenerator;
import com.nikhildagrawal.flix_android.models.Movie;
import com.nikhildagrawal.flix_android.responses.MovieDetailResponse;
import com.nikhildagrawal.flix_android.responses.MovieSearchResponse;
import com.nikhildagrawal.flix_android.util.Constants;
import com.nikhildagrawal.flix_android.viewmodels.MovieViewModel;

import java.util.List;


public class MovieListActivity extends BaseActivity implements ItemClickListner {


    private static final String TAG = "MovieListActivity";
    private MovieViewModel mMovieViewModel;
    private RecyclerView mRecyclerView;
    private MovieListAdapter mAdapter;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("NETFLIX");


        mRecyclerView = findViewById(R.id.movie_recyclerview);
        mMovieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

        initRecyclerView();
        subscribeObserver();
        testRetrofit();
    }

    private void initRecyclerView(){
        mAdapter = new MovieListAdapter(this,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }


    public void subscribeObserver(){

        mMovieViewModel.getMoviesList().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {

                for(Movie movie: movies ){
                    Log.d(TAG, "Movie: " + movie.getTitle());
                }

                mAdapter.setmMovies(movies);
                mAdapter.notifyDataSetChanged();

            }
        });

    }


    private void searchMoviesApi(String query, int page){

        mMovieViewModel.searchMoviesApi(query,page);
    }

    public void  testRetrofit(){
        searchMoviesApi("Avengers",1);
    }

    @Override
    public void onItemClick(int position) {

        Toast.makeText(this,"Position is: "+ position,Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,MovieDetailsActivity.class);
        intent.putExtra("Position",position);
        startActivity(intent);

    }


//
//    private void testRetrofit(){
//
//        MoviesApi moviesApi = ServiceGenerator.getMoviesApi();
//
//        Call<MovieSearchResponse> responseCall = moviesApi.searchMovies(Constants.API_KEY,"Avengers","1");
//
//
//        responseCall.enqueue(new Callback<MovieSearchResponse>() {
//            @Override
//            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
//                Log.d(TAG,"Raw Response from server: " + response );
//
//                if(response.code() == 200){
//                    Log.d(TAG,"RESPONSE BODY: " + response.body().toString());
//
//                    List<Movie>  moviesResult = response.body().getMovies();
//
//                    for(Movie movie: moviesResult){
//                        Log.d(TAG,"RESPONSE : " + movie.getTitle());
//                    }
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
//
//            }
//        });
//
//
//    }


//    private void testRetrofitDetails(){
//
//        // I need retrofit obj which i will get from service generator
//
//        MoviesApi moviesApi = ServiceGenerator.getMoviesApi();
//
//        Call<MovieDetailResponse> responseCall = moviesApi.getMovieDetail("299534",Constants.API_KEY);
//
//        responseCall.enqueue(new Callback<MovieDetailResponse>() {
//            @Override
//            public void onResponse(Call<MovieDetailResponse> call, Response<MovieDetailResponse> response) {
//                Log.d(TAG,"Raw Response from server: " + response );
//
//                if(response.code() == 200){
//                    Log.d(TAG,"RESPONSE BODY: " + response.body().toString());
//
//
//                    Log.d(TAG,"RESPONSE : " + response.body().getMovie());
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<MovieDetailResponse> call, Throwable t) {
//
//            }
//        });
//    }
}
