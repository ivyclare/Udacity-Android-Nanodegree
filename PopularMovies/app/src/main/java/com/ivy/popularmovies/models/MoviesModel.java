package com.ivy.popularmovies.models;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

public class MoviesModel extends RealmObject implements Parcelable {

    private String release_date;
    private String title;
    private String poster_path;
    private String overview;
    private float vote_average;
    private String backdrop_path;
    private String id;

    public MoviesModel(){

    }

    public MoviesModel(MoviesModel movieModel){
        release_date = movieModel.release_date;
        title = movieModel.title;
        poster_path = movieModel.poster_path;
        overview = movieModel.overview;
        vote_average = movieModel.vote_average;
        backdrop_path = movieModel.backdrop_path;
        id = movieModel.id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(poster_path);
        dest.writeFloat(vote_average);
        dest.writeString(backdrop_path);
        dest.writeString(overview);
        dest.writeString(release_date);
        dest.writeString(id);

    }

    protected MoviesModel(Parcel in) {
        title = in.readString();
        poster_path = in.readString();
        vote_average = in.readFloat();
        backdrop_path = in.readString();
        overview = in.readString();
        release_date = in.readString();
        id = in.readString();
    }

    public static final Creator<MoviesModel> CREATOR = new Creator<MoviesModel>() {
        @Override
        public MoviesModel createFromParcel(Parcel in) {
            return new MoviesModel(in);
        }

        @Override
        public MoviesModel[] newArray(int size) {
            return new MoviesModel[size];
        }
    };

    public String getTitle() {
        return title;
    }



    public String getposter_path() {
        return poster_path;
    }




    public String getOverview() {
        return overview;
    }



    public float getvote_average() {
        return vote_average;
    }



    public String getrelease_date() {
        return release_date;
    }




    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getId() {
        return id;
    }




}
