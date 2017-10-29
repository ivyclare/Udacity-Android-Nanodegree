package com.ivy.popularmovies.data.remote;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import com.ivy.popularmovies.data.models.MovieModel;
import com.ivy.popularmovies.data.models.ReviewModel;
import com.ivy.popularmovies.data.models.TrailerModel;

import java.util.List;

/**
 * Created by Ivoline-Clarisse on 10/28/2017.
 */
public interface MovieService {

    @GET("/3/movie/{sort}")
    Call<Movies> loadMovies(@Path("sort") String sort, @Query("api_key") String api_key);

    @GET("/3/movie/{id}/videos")
    Call<Trailers> loadTrailers(@Path("id") String id, @Query("api_key") String api_key);

    @GET("/3/movie/{id}/reviews")
    Call<Reviews> loadReviews(@Path("id") String id, @Query("api_key") String api_key);

    class Movies {
        public List<MovieModel> results;
    }

    class Reviews {
        public List<ReviewModel> results;
    }

    class Trailers {
        public List<TrailerModel> results;
    }

}
