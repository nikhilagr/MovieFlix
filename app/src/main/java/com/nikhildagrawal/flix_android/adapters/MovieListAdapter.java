package com.nikhildagrawal.flix_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nikhildagrawal.flix_android.ItemClickListner;
import com.nikhildagrawal.flix_android.R;
import com.nikhildagrawal.flix_android.models.Movie;
import com.nikhildagrawal.flix_android.util.Constants;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MoviesViewHolder> {


    private List<Movie> mMovies;
    private ItemClickListner mListner;

    Context mContext;

    public MovieListAdapter(Context mContext, ItemClickListner listner) {
        this.mContext = mContext;
        this.mListner = listner;
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item,parent,false);

        MoviesViewHolder holder = new MoviesViewHolder(view,mListner);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.ic_launcher_background);

        Glide.with(holder.itemView.getContext()).setDefaultRequestOptions(requestOptions)
                .load(Constants.BASE_IMAGE_URL+Constants.IMAGE_SIZE+mMovies.get(position).getPoster_path())
                .into(holder.mImagePoster);


    }

    @Override
    public int getItemCount() {

        if(mMovies!=null){
            return mMovies.size();
        }
        return 0;
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        ImageView mImagePoster;
      //  TextView mTVTitle;
        public MoviesViewHolder(@NonNull View itemView, ItemClickListner itemClickListner) {
            super(itemView);

            mImagePoster = itemView.findViewById(R.id.movie_list_item_poster);
            mListner = itemClickListner;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            mListner.onItemClick(getAdapterPosition());
        }
    }




    public void setmMovies(List<Movie> movies){
        mMovies = movies;
        this.notifyDataSetChanged();
    }
}




