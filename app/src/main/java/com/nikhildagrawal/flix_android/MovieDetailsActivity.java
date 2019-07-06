package com.nikhildagrawal.flix_android;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nikhildagrawal.flix_android.models.Movie;
import com.nikhildagrawal.flix_android.util.Constants;
import com.nikhildagrawal.flix_android.viewmodels.MovieViewModel;

import java.util.List;

public class MovieDetailsActivity extends BaseActivity {


    TextView mTitle,mVote,mDescription;
    ImageView mPoster;
    MovieViewModel mMovieViewModel;
    int mPosition;
    Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        mTitle = findViewById(R.id.tv_title_details);
        mVote = findViewById(R.id.tv_vote_details);
        mDescription = findViewById(R.id.tv_overview_Details);
        mPoster = findViewById(R.id.image_moviedetails);
        mPosition  = getIntent().getIntExtra("Position",0);
        mToolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(mToolbar);





        mMovieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        mMovieViewModel.getMoviesList().observe(this, new Observer<List<Movie>>() {

            @Override
            public void onChanged(List<Movie> movies) {

                mTitle.setText(movies.get(mPosition).getTitle());
                mVote.setText(String.valueOf(movies.get(mPosition).getVote_average())+"/10");
                mDescription.setText(movies.get(mPosition).getOverview());

                RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.ic_launcher_background);
                Glide.with(getBaseContext()).setDefaultRequestOptions(requestOptions)
                        .load(Constants.BASE_IMAGE_URL+Constants.IMAGE_SIZE+movies.get(mPosition).getPoster_path())
                        .into(mPoster);

            }
        });


    }
}
