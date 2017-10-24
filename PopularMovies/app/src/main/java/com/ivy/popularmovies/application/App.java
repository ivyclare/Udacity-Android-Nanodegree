package com.ivy.popularmovies.application;


import android.app.Application;

import com.ivy.popularmovies.utils.API;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {
    private static API.MovieClient movieClient;

    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration config = new RealmConfiguration.Builder(getApplicationContext()).deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);
        movieClient = new API.MovieClient();
    }

    public static API.MovieClient getMovieClient() {
        return movieClient;
    }
}
