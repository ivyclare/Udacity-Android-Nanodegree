package com.ivy.popularmovies.data.remote;

/**
 * Created by Ivoline-Clarisse on 10/28/2017.
 */
public class ApiUtils {

    public static final String API_KEY = "[YOUR_API_KEY]";
    public static final String IMAGE_URL = "http://image.tmdb.org/t/p";
    public static final String BASE_URL = "http://api.themoviedb.org";

    public static MovieService getMovieService() {
        return MovieClient.getClient(BASE_URL).create(MovieService.class);
    }
}
