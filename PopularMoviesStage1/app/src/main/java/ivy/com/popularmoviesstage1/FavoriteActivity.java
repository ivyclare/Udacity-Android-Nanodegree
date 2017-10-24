package ivy.com.popularmoviesstage1;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import ivy.com.popularmoviesstage1.adapters.FavoritesListAdapter;
import ivy.com.popularmoviesstage1.adapters.MovieAdapter;
import ivy.com.popularmoviesstage1.data.FavoriteContract;
import ivy.com.popularmoviesstage1.model.Favorite;
import ivy.com.popularmoviesstage1.model.Movie;
import ivy.com.popularmoviesstage1.util.Utils;

// First activity that appears, shows list of movies
public class FavoriteActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private FavoritesListAdapter fav_adapter;
    private ArrayList<Favorite> favorites;

    private RecyclerView favRecyclerView;
    private MovieAdapter adapter;
    //private ArrayList<Movie> movieList;
    private ProgressBar mProgressBar;
    Favorite movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        mProgressBar = (ProgressBar) findViewById(R.id.favProgressBar);

        //Initialising Views
        favRecyclerView = (RecyclerView) findViewById(R.id.fav_recycler_view);

//        movieList = new ArrayList<>();
//        adapter = new MovieAdapter(this, movieList);

        favorites = getFavoriteMovies();
        if(favorites.isEmpty()){
            Toast.makeText(this,"No favorite movies have been added",Toast.LENGTH_LONG).show();
        }else {
            fav_adapter = new FavoritesListAdapter(this, favorites); //If it doesnt work change AsyncTask to a method and try to get te favorites and adapt it here
            favRecyclerView.setAdapter(fav_adapter);

            //Setting an OnClickListener in the RecyclerView
            //**** Recycler View Containing Different Sections(Modules) of App
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
            favRecyclerView.setLayoutManager(mLayoutManager);
            favRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
            favRecyclerView.setItemAnimator(new DefaultItemAnimator());
            favRecyclerView.setAdapter(adapter);
        }
        // Called when any item is clicked on the list
        favRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(),
                        favRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        Intent intent = new Intent(FavoriteActivity.this, MovieDetailActivity.class);

                        movie = favorites.get(position);

                        intent.putExtra("title", movie.getTitle()).
                                putExtra("overview", movie.getOverview());
//                                putExtra("average", movie.getAverage()).
//                                putExtra("date", movie.getDate()).
//                                putExtra("image", movie.getImage());

                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menuSortPopular) {
//            GetSpecificUrl(Utils.URL_POP);
        } else if (id == R.id.menuSortRating) {
//            GetSpecificUrl(Utils.URL_RATED);
        } else if (id == R.id.menuSortFav) {
//            //Fill list here
//            System.out.println("ENTERS URL_POP CONNECT");
//            //favorites.clear();
//            favorites = getFavoriteMovies();
//            //new AsyncDbTask().execute();
//
//            fav_adapter = new FavoritesListAdapter(this, favorites); //If it doesnt work change AsyncTask to a method and try to get te favorites and adapt it here
//            favRecyclerView.setAdapter(fav_adapter);
//          //mProgressBar.setVisibility(View.VISIBLE);
        }

        return super.onOptionsItemSelected(item);
    }

    // Code to get Favorite Movies from database
    private class AsyncDbTask extends AsyncTask<Void, Void, ArrayList<Favorite>> {
        @Override
        protected ArrayList<Favorite> doInBackground(Void... params) {
            ArrayList<Favorite> favorites = new ArrayList<>();
            Cursor cursor = getApplicationContext().getContentResolver()
                    .query(
                            FavoriteContract.MovieEntry.CONTENT_URI,
                            new String[]{
                                    FavoriteContract.MovieColumns.MOVIE_ID,
                                    FavoriteContract.MovieColumns.MOVIE_TITLE,
                                    FavoriteContract.MovieColumns.MOVIE_RELEASE_DATE,
                                    FavoriteContract.MovieColumns.MOVIE_DURATION,
                                    FavoriteContract.MovieColumns.MOVIE_RATING,
                                    FavoriteContract.MovieColumns.MOVIE_POSTER_PATH,
                                    FavoriteContract.MovieColumns.MOVIE_BACKDROP_PATH
                            },
                            null, null, null
                    );
            if (cursor == null) {
                return null;
            }

            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex(FavoriteContract.MovieColumns.MOVIE_ID));
                String title = cursor.getString(cursor.getColumnIndex(FavoriteContract.MovieColumns.MOVIE_TITLE));
                String releaseDate = cursor.getString(cursor.getColumnIndex(FavoriteContract.MovieColumns.MOVIE_RELEASE_DATE));
                int duration = cursor.getInt(cursor.getColumnIndex(FavoriteContract.MovieColumns.MOVIE_DURATION));
                double rating = cursor.getDouble(cursor.getColumnIndex(FavoriteContract.MovieColumns.MOVIE_RATING));
                String posterPath = cursor.getString(cursor.getColumnIndex(FavoriteContract.MovieColumns.MOVIE_POSTER_PATH));
                String backdropPath = cursor.getString(cursor.getColumnIndex(FavoriteContract.MovieColumns.MOVIE_BACKDROP_PATH));
                favorites.add(new Favorite(id, title, releaseDate, duration, rating, posterPath, backdropPath));
            }
            cursor.close();
            return favorites;
        }

        @Override
        protected void onPostExecute(ArrayList<Favorite> favorites) {
            super.onPostExecute(favorites);
            addFavoriteMovies(favorites);
        }
    }

    //Adding and Updating Favorite Tale
    protected void addMovies(List<Favorite> favorites) {
        int numMovies = this.favorites.size();
        int numMoviesDownloaded = favorites.size();
        this.favorites.addAll(favorites);
        updateList(numMovies, numMoviesDownloaded);
    }

    private void updateList(int numMovies, int numMoviesDownloaded) {
//        recyclerView.setIsLoading(false);
//
//        /*
//        If it was the first page that was loaded,
//        progressBar must be visible. Make it invisible.
//         */
//        if (page == 1) {
//            adapter.notifyDataSetChanged();
//            progressBar.setVisibility(View.INVISIBLE);
//            recyclerView.setVisibility(View.VISIBLE);
//            return;
//        }
//        recyclerView.loadingComplete();
        adapter.notifyItemRangeInserted(numMovies, numMoviesDownloaded);
    }


    protected void addFavoriteMovies(List<Favorite> favorites) {
        this.favorites.clear();

        this.favorites.addAll(favorites);
        adapter.notifyDataSetChanged();
       // progressBar.setVisibility(View.INVISIBLE);
//        if (favorites.size() == 0) {
//            recyclerView.setState(Constants.NONE);
//        } else {
//            recyclerView.setState(Constants.DONE);
//        }
    }

    //Getting the favorite movies from the database
    public ArrayList<Favorite> getFavoriteMovies() {
       System.out.println("METHOD IS WORKINGGGGGGGGGGGGGGG");
        ArrayList<Favorite> favorites = new ArrayList<>();
        Cursor cursor = getApplicationContext().getContentResolver()
                .query(
                        FavoriteContract.MovieEntry.CONTENT_URI,
                        new String[]{
                                FavoriteContract.MovieColumns.MOVIE_ID,
                                FavoriteContract.MovieColumns.MOVIE_TITLE,
                                FavoriteContract.MovieColumns.MOVIE_RELEASE_DATE,
                                FavoriteContract.MovieColumns.MOVIE_DURATION,
                                FavoriteContract.MovieColumns.MOVIE_RATING,
                                FavoriteContract.MovieColumns.MOVIE_POSTER_PATH,
                                FavoriteContract.MovieColumns.MOVIE_BACKDROP_PATH
                        },
                        null, null, null
                );
        if (cursor == null) {
            return null;
        }

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(FavoriteContract.MovieColumns.MOVIE_ID));
            String title = cursor.getString(cursor.getColumnIndex(FavoriteContract.MovieColumns.MOVIE_TITLE));
            String releaseDate = cursor.getString(cursor.getColumnIndex(FavoriteContract.MovieColumns.MOVIE_RELEASE_DATE));
            int duration = cursor.getInt(cursor.getColumnIndex(FavoriteContract.MovieColumns.MOVIE_DURATION));
            double rating = cursor.getDouble(cursor.getColumnIndex(FavoriteContract.MovieColumns.MOVIE_RATING));
            String posterPath = cursor.getString(cursor.getColumnIndex(FavoriteContract.MovieColumns.MOVIE_POSTER_PATH));
            String backdropPath = cursor.getString(cursor.getColumnIndex(FavoriteContract.MovieColumns.MOVIE_BACKDROP_PATH));
            favorites.add(new Favorite(id, title, releaseDate, duration, rating, posterPath, backdropPath));
        }
        cursor.close();
        return favorites;
    }

}

