package com.ivy.popularmovies.utils;

import com.ivy.popularmovies.models.MoviesModel;
import com.ivy.popularmovies.models.ReviewModel;
import com.ivy.popularmovies.models.TrailerModel;

import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

import com.ivy.popularmovies.utils.Util;

public interface API {

    @GET("/3/movie/{sort}")
    Call<Movies> loadMovies(@Path("sort") String sort, @Query("api_key") String api_key);

    @GET("/3/movie/{id}/videos")
    Call<Trailers> loadTrailers(@Path("id") String id, @Query("api_key") String api_key);

    @GET("/3/movie/{id}/reviews")
    Call<Reviews> loadReviews(@Path("id") String id, @Query("api_key") String api_key);


    class MovieClient
    {
        private API mAPI;


        public MovieClient()
        {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Util.ROOT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            mAPI = retrofit.create(API.class);
        }

        public API getMovieAPI()
        {
            return mAPI;
        }
    }

    class Movies {
        public List<MoviesModel> results;
    }

    class Reviews {
        public List<ReviewModel> results;
    }

    class Trailers {
        public List<TrailerModel> results;
    }
}
