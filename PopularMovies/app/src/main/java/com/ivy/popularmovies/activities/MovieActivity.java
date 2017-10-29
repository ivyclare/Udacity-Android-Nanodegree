package com.ivy.popularmovies.activities;

import  android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ivy.popularmovies.data.database.MovieContract;
import com.ivy.popularmovies.data.remote.ApiUtils;
import com.ivy.popularmovies.data.remote.MovieService;
import com.ivy.popularmovies.R;
import com.ivy.popularmovies.adapters.MoviesAdapter;
import com.ivy.popularmovies.data.models.MovieModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MovieActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Toolbar toolbar;
    private MovieService mService;
    public static ProgressBar progressBar;
    ArrayList<MovieModel> popList;
    ArrayList<MovieModel> ratedList;
    ArrayList<MovieModel> favList;
    MoviesAdapter popAdapter;
    MoviesAdapter ratedAdapter;
    MoviesAdapter favAdapter;

    GridLayoutManager mLayoutManager;
    private boolean mTwoPane;
    String SORT_BY = "POPULAR";
    NetworkReceiver networkReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        mService = ApiUtils.getMovieService();

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        if (progressBar != null) {
            progressBar.setVisibility(View.INVISIBLE);
        }
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //**** Recycler View Containing Different Sections(Modules) of App
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        

        popList = new ArrayList<>();
        ratedList = new ArrayList<>();
        favList = new ArrayList<>();

        popAdapter = new MoviesAdapter(this, popList);
        ratedAdapter = new MoviesAdapter(this, ratedList);
        favAdapter = new MoviesAdapter(this,favList);

        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        networkReceiver = new NetworkReceiver();
        registerReceiver(networkReceiver,intentFilter);


        if (savedInstanceState!=null)
        {
            popAdapter.addAll(savedInstanceState.<MovieModel>getParcelableArrayList("POP"));
            ratedAdapter.addAll(savedInstanceState.<MovieModel>getParcelableArrayList("RATED"));
            SORT_BY = savedInstanceState.getString("SORT_BY");
        }

        else {
            if (isConnectionAvailable()) {

                (new GetMovies()).execute("popular");
                (new GetMovies()).execute("top_rated");
            }
        }
        if (findViewById(R.id.movie_detail_container) != null) {
            mTwoPane = true;
        }
        createRecyclerView();

        getFavoriteMovies();


        recyclerView.addOnItemTouchListener(new RecyclerClickListener(this, new RecyclerClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                MovieModel movieModel;
                switch (SORT_BY) {
                    case "POPULAR":
                        movieModel = popList.get(position);
                        break;
                    case "RATED":
                        movieModel = ratedList.get(position);
                        break;
                    default:
                        movieModel = favList.get(position);
                        break;
                }

                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putParcelable("movie", movieModel);
                    DetailFragment fragment = new DetailFragment();
                    fragment.setArguments(arguments);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.movie_detail_container, fragment)
                            .commit();
                } else {
                    Intent intents = new Intent(getApplicationContext(), DetailActivity.class);
                    intents.putExtra("movie", movieModel);
                    startActivity(intents);
                }

            }
        }));

    }

    //Setting the Recycler View
    public void createRecyclerView() {
        mLayoutManager = new GridLayoutManager(this, 2);
        
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mLayoutManager.setSpanCount(2);
            recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        }else {
            mLayoutManager.setSpanCount(3);
            recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(10), true));
        }

        switch (SORT_BY){
            case "POPULAR" : recyclerView.setAdapter(popAdapter);
                break;
            case "RATED" : recyclerView.setAdapter(ratedAdapter);
                break;
            case "FAVORITE" : recyclerView.setAdapter(favAdapter);
        }

        recyclerView.setLayoutManager(mLayoutManager);
    }


    //Asyntask to get movie data
    private class GetMovies extends AsyncTask<String, Void,
                List<MovieModel>> {
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected List<MovieModel> doInBackground(String... params) {
            final String sort = params[0];

            mService.loadMovies(sort, ApiUtils.API_KEY).enqueue(new Callback<MovieService.Movies>() {
                @Override
                public void onResponse(Call<MovieService.Movies> call, Response<MovieService.Movies> response) {

                    if(response.isSuccessful()) {
                       // mAdapter.updateAnswers(response.body().getItems());

                        if (sort.equals("popular")) {
                            for (int i = 0; i < response.body().results.size(); i++) {
                                popList.add(response.body().results.get(i));
                            }
                            popAdapter.notifyDataSetChanged();
                        } else {
                            for (int i = 0; i < response.body().results.size(); i++) {
                                ratedList.add(response.body().results.get(i));
                            }
                            ratedAdapter.notifyDataSetChanged();
                        }
                        if (mTwoPane && popList.size()!=0) {
                            Bundle arguments = new Bundle();
                            arguments.putParcelable("movie", popList.get(0));
                            DetailFragment fragment = new DetailFragment();
                            fragment.setArguments(arguments);
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.movie_detail_container, fragment)
                                    .commit();
                        }

                        Log.d("MainActivity", "posts loaded from API");
                    }else {
                        int statusCode  = response.code();
                        // handle request errors depending on status code
                    }
                }

                @Override
                public void onFailure(Call<MovieService.Movies> call, Throwable t) {
                    //showErrorMessage();
                    Toast.makeText(getApplicationContext(),"Error while loading Movies",Toast.LENGTH_LONG).show();
                    Log.d("MainActivity", "error loading from API");

                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(List<MovieModel> movieModels) {

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        
        //noinspection SimplifiableIfStatement
        if (id == R.id.menuSortPopular) {
            recyclerView.setAdapter(popAdapter);
            SORT_BY = "POPULAR";
        } else if (id == R.id.menuSortRating) {
            recyclerView.setAdapter(ratedAdapter);
            SORT_BY = "RATED";
        }else if (id == R.id.menuSortFav) {
            recyclerView.setAdapter(favAdapter);
            SORT_BY = "FAVORITE";
            getFavoriteMovies();
        }
        
        item.setChecked(true);

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mLayoutManager.setSpanCount(3);

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            mLayoutManager.setSpanCount(2);
        }
    }

    private boolean isConnectionAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    // Get favorite movies from database
    public void getFavoriteMovies() {

        favList.clear();
       // ArrayList<MovieModel> movies = new ArrayList<>();
        Cursor cursor = getApplicationContext().getContentResolver()
                .query(
                        MovieContract.MovieEntry.CONTENT_URI,
                        new String[]{
                                MovieContract.MovieColumns.MOVIE_ID,
                                MovieContract.MovieColumns.MOVIE_TITLE,
                                MovieContract.MovieColumns.MOVIE_RELEASE_DATE,
                                MovieContract.MovieColumns.MOVIE_VOTE_AVERAGE,
                                MovieContract.MovieColumns.MOVIE_OVERVIEW,
                                MovieContract.MovieColumns.MOVIE_POSTER_PATH,
                                MovieContract.MovieColumns.MOVIE_BACKDROP_PATH
                        },
                        null, null, null
                );

        if (cursor == null) {
            Toast.makeText(getApplicationContext(),"No Movies Have been Marked as Favorite",Toast.LENGTH_LONG).show();
        }else{
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex(MovieContract.MovieColumns.MOVIE_ID));
                String title = cursor.getString(cursor.getColumnIndex(MovieContract.MovieColumns.MOVIE_TITLE));
                String releaseDate = cursor.getString(cursor.getColumnIndex(MovieContract.MovieColumns.MOVIE_RELEASE_DATE));
                double average = cursor.getInt(cursor.getColumnIndex(MovieContract.MovieColumns.MOVIE_VOTE_AVERAGE));
                String overview = cursor.getString(cursor.getColumnIndex(MovieContract.MovieColumns.MOVIE_OVERVIEW));
                String posterPath = cursor.getString(cursor.getColumnIndex(MovieContract.MovieColumns.MOVIE_POSTER_PATH));
                String backdropPath = cursor.getString(cursor.getColumnIndex(MovieContract.MovieColumns.MOVIE_BACKDROP_PATH));

                favList.add(new MovieModel(id,title,releaseDate,overview,average, posterPath, backdropPath));
                System.out.println("THE favorite movies ADDED ARE "+favList);
            }
            favAdapter.notifyDataSetChanged();
            cursor.close();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList("POP", popList);
        outState.putParcelableArrayList("RATED",ratedList);
        outState.putString("SORT_BY",SORT_BY);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkReceiver);
    }

    public class NetworkReceiver extends BroadcastReceiver {
        public NetworkReceiver(){

        }
        @Override
        public void onReceive(Context context, Intent intent) {
                    (new GetMovies()).execute("popular");
                    (new GetMovies()).execute("top_rated");
        }
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
