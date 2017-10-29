package com.ivy.popularmovies.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.ivy.popularmovies.R;
import com.ivy.popularmovies.data.remote.ApiUtils;
import com.squareup.picasso.Picasso;
import com.ivy.popularmovies.data.models.MovieModel;

public class DetailActivity extends AppCompatActivity {

    ImageView toolImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        MovieModel movieModel =  getIntent().getParcelableExtra("movie");
        toolImage = (ImageView)findViewById(R.id.toolImage);

        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
            Picasso.with(this).load(ApiUtils.IMAGE_URL + "/w500" + movieModel.getBackdropPath() + "?api_key?=" + ApiUtils.API_KEY).placeholder(R.drawable.image).error(R.drawable.image).into(toolImage);
        }


        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putParcelable("movie", movieModel);
            DetailFragment fragment = new DetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_detail_container, fragment)
                    .commit();
        }
    }


}
