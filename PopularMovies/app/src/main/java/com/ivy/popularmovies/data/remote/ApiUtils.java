package com.ivy.popularmovies.data.remote;

/**
 * Created by Ivoline-Clarisse on 10/28/2017.
 */
public class ApiUtils {

    public static final String API_KEY = "1b9c7386849e73058df501d08dd50a6a";
    public static final String IMAGE_URL = "http://image.tmdb.org/t/p";
    public static final String BASE_URL = "http://api.themoviedb.org";

    public static MovieService getMovieService() {
        return MovieClient.getClient(BASE_URL).create(MovieService.class);
    }
}
