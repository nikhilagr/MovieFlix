package com.nikhildagrawal.flix_android.viewmodels;
import com.nikhildagrawal.flix_android.models.Movie;
import com.nikhildagrawal.flix_android.repositories.MovieRepository;
import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MovieViewModel extends ViewModel {

    private MovieRepository mMovieRepository;



    public MovieViewModel(){
      mMovieRepository = MovieRepository.getInstance();
    }

    public LiveData<List<Movie>> getMoviesList(){

        return mMovieRepository.getMoviesList();

    }

    public void searchMoviesApi(String query, int page){
        if(page <= 0){
            page = 1;
        }
        mMovieRepository.searchMoviesApi(query,page);
    }
}
