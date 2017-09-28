package ivy.com.popularmoviesstage1;

import android.content.Intent;
import android.content.res.Resources;
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
import android.widget.ImageView;
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

import ivy.com.popularmoviesstage1.adapters.MovieAdapter;
import ivy.com.popularmoviesstage1.util.Movie;
import ivy.com.popularmoviesstage1.util.Utils;

// First activity that appears, shows list of movies
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private ArrayList<Movie> movieList;
    private ProgressBar mProgressBar;
    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        //Initialising Views
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        movieList = new ArrayList<>();
        adapter = new MovieAdapter(this, movieList);

    //Setting an OnClickListener in the RecyclerView
        //**** Recycler View Containing Different Sections(Modules) of App
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        //Displays Popular Movies by default
        GetSpecificUrl(Utils.URL_POP);

        // Called when any item is clicked on the list
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(),
                        recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);

                        movie = movieList.get(position);

                        intent.putExtra("title", movie.getTitle()).
                                putExtra("overview", movie.getOverview()).
                                putExtra("average", movie.getAverage()).
                                putExtra("date", movie.getDate()).
                                putExtra("image", movie.getImage());

                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }

    // Method to get specific url for the category to be displayed
    private void GetSpecificUrl(String url) {
        System.out.println("ENTERS URL_POP CONNECT");
        movieList.clear();
        new LoadMovieTask().execute(url);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    // Task to get movie data from api
    public class LoadMovieTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected Integer doInBackground(String... params) {
            Integer result = 0;
            try {
                // Create Apache HttpClient
                System.out.println("THE URL_POP IS "+params[0]);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse httpResponse = httpclient.execute(new HttpGet(params[0]));
                int statusCode = httpResponse.getStatusLine().getStatusCode();

                System.out.println("******* THE STATUS CODE IS "+statusCode);
                // 200 represents HTTP OK
                if (statusCode == 200) {
                    String response = streamToString(httpResponse.getEntity().getContent());
                    System.out.println("RESPONSE FROM SERVER "+response);
                    parseResult(response);
                    result = 1; // Successful
                } else {
                    result = 0; //"Failed
                }
            } catch (Exception e) {
                Log.d(Utils.TAG, e.getLocalizedMessage());
            }

            return result;
        }

        @Override
        protected void onPostExecute(Integer result) {
            if (result == 1) {
                adapter.setMovieData(movieList);
            } else {
                Toast.makeText(MainActivity.this, getString(R.string.error), Toast.LENGTH_SHORT).show();
            }

            mProgressBar.setVisibility(View.GONE);
        }
    }

    // Method that converts the input gotten to String
    String streamToString(InputStream stream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        String line;
        String result = "";
        while ((line = bufferedReader.readLine()) != null) {
            result += line;
        }

        if (null != stream) {
            stream.close();
        }
        return result;
    }

    //Gets the results from JSON data
    private void parseResult(String result) {
        try {
            String mTitle, mPath, mOverview, mAverage, mDate;
            JSONObject jsonobject = new JSONObject(result);
            JSONArray jsonArray = jsonobject.getJSONArray("results");
            for(int i = 0; i < jsonArray.length(); i++) {
                movie = new Movie();
                JSONObject json = jsonArray.getJSONObject(i);
                mTitle = json.getString("original_title");
                mPath = json.getString("poster_path");
                mOverview = json.getString("overview");
                mAverage = json.getString("vote_average");
                mDate = json.getString("release_date");

                String img_url = Utils.mUrl + mPath;
                movie.setImage(img_url);
                movie.setTitle(mTitle);
                movie.setOverview(mOverview);
                movie.setAverage(mAverage);
                movie.setDate(mDate);
                movieList.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
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
            GetSpecificUrl(Utils.URL_POP);
        } else if (id == R.id.menuSortRating) {
            GetSpecificUrl(Utils.URL_RATED);
        }

        return super.onOptionsItemSelected(item);
    }
}
