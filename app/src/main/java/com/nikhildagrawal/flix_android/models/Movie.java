package com.nikhildagrawal.flix_android.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    private String id;
    private String title;
    private String overview;
    private float  vote_average;
    private String poster_path;
    private String release_date;

    public Movie() {
    }

    public Movie(String id, String title, String overview, float vote_average, String poster_path,
                 String release_date) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.vote_average = vote_average;
        this.poster_path = poster_path;
        this.release_date = release_date;
    }


    protected Movie(Parcel in) {
        id = in.readString();
        title = in.readString();
        overview = in.readString();
        vote_average = in.readFloat();
        poster_path = in.readString();
        release_date = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", vote_average=" + vote_average +
                ", poster_path='" + poster_path + '\'' +
                ", release_date='" + release_date + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(overview);
        dest.writeFloat(vote_average);
        dest.writeString(poster_path);
        dest.writeString(release_date);
    }
}
