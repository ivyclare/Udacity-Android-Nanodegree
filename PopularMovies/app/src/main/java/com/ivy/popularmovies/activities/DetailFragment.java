package com.ivy.popularmovies.activities;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ivy.popularmovies.data.database.MovieContract;
import com.ivy.popularmovies.data.remote.ApiUtils;
import com.ivy.popularmovies.data.remote.MovieService;
import com.ivy.popularmovies.R;
import com.squareup.picasso.Picasso;
import com.ivy.popularmovies.adapters.ReviewAdapter;
import com.ivy.popularmovies.adapters.TrailerAdapter;
import com.ivy.popularmovies.data.models.MovieModel;
import com.ivy.popularmovies.data.models.ReviewModel;
import com.ivy.popularmovies.data.models.TrailerModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailFragment extends Fragment {

    private MovieService mService;
    MovieModel movieModel;
    ImageView thumbnail;
    ImageView favButton;
    CollapsingToolbarLayout appBarLayout;
    TextView rates;
    RatingBar ratesBar;
    TextView overview;
    TextView releaseInfo;
    RecyclerView trailersRecyclerView;
    RecyclerView reviewsRecyclerView;
    TextView noReviewView;
    TextView noTrailerView;
    LinearLayout extraLayout;
    ArrayList<TrailerModel> trailerList;
    ArrayList<ReviewModel> reviewList;
    ReviewAdapter reviewAdapter;
    TrailerAdapter trailerAdapter;
    Boolean isFavorite;
    //Realm realm = Realm.getDefaultInstance();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        mService = ApiUtils.getMovieService();

        if (getArguments().containsKey("movie")) {

            Activity activity = this.getActivity();
            appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.collapsing_toolbar);
            if (appBarLayout != null) {
                appBarLayout.setTitle("");
            }
            movieModel = getArguments().getParcelable("movie");
            assert movieModel != null;
        }
        trailerList = new ArrayList<>();
        reviewList = new ArrayList<>();
        (new FetchReviews()).execute(movieModel.getId().toString());
        (new FetchTrailers()).execute(movieModel.getId().toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.movie_detail, container, false);

        thumbnail = (ImageView) rootView.findViewById(R.id.imageView);
        rates = (TextView) rootView.findViewById(R.id.rating);
        ratesBar = (RatingBar ) rootView.findViewById(R.id.ratingBar);
        overview = (TextView) rootView.findViewById(R.id.overview);
        releaseInfo = (TextView) rootView.findViewById(R.id.releaseText);
        trailersRecyclerView = (RecyclerView) rootView.findViewById(R.id.trailersRecyclerView);
        reviewsRecyclerView = (RecyclerView) rootView.findViewById(R.id.reviewsRecyclerView);
        noReviewView = (TextView) rootView.findViewById(R.id.noReviewView);
        noTrailerView = (TextView) rootView.findViewById(R.id.noTrailerView);

        favButton = (ImageView) rootView.findViewById(R.id.favButton);
        favButton.setImageResource(!isFavorite() ? R.drawable.remove_fav : R.drawable.add_fav);

        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ToggleFavorite().execute();
            }
        });


        appBarLayout.setTitle(movieModel.getTitle());
        Picasso.with(getActivity()).load(ApiUtils.IMAGE_URL+"/w342" + movieModel.getPosterPath() + "?api_key?=" + ApiUtils.API_KEY).placeholder(R.drawable.image).error(R.drawable.image).into(thumbnail);

        rates.setText(Double.toString(movieModel.getVoteAverage()).concat("/10"));
        ratesBar.setMax(5);
        ratesBar.setRating((float)(movieModel.getVoteAverage() / 2f));

        overview.setText(movieModel.getOverview());
        releaseInfo.setText("Release Date: ".concat(movieModel.getReleaseDate()));

        if(!isNetworkAvailable())
            extraLayout.setVisibility(View.INVISIBLE);

        LinearLayoutManager trailerLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager reviewLayoutManager = new LinearLayoutManager(getContext());

        trailersRecyclerView.setLayoutManager(trailerLayoutManager);
        reviewsRecyclerView.setLayoutManager(reviewLayoutManager);

        reviewAdapter = new ReviewAdapter(getContext(),reviewList);
        trailerAdapter = new TrailerAdapter(getContext(),trailerList);

        trailersRecyclerView.setAdapter(trailerAdapter);
        trailersRecyclerView.addOnItemTouchListener(new RecyclerClickListener(getContext(), new RecyclerClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String url = "https://www.youtube.com/watch?v=".concat(trailerList.get(position).getKey());
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }

        }));

        reviewsRecyclerView.setAdapter(reviewAdapter);
        reviewsRecyclerView.addOnItemTouchListener(new RecyclerClickListener(getContext(), new RecyclerClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(reviewList.get(position).getUrl()));
                startActivity(intent);
            }
        }));

        new isFavorite().execute();
        return rootView;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.detail_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.share:
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_SUBJECT, movieModel.getTitle());
                share.putExtra(Intent.EXTRA_TEXT, "https://www.youtube.com/watch?v=".concat(trailerList.get(0).getKey()));
                startActivity(Intent.createChooser(share, "Share Trailer!"));
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    private boolean isFavorite(){

        //return realm.where(MovieModel.class).contains("id", movieModel.getId()).findAll().size() != 0;
        return false;
    }

    private class FetchReviews extends AsyncTask<String, Void,
            List<ReviewModel>> {


        @Override
        protected void onPreExecute() {

        }

        @Override
        protected List<ReviewModel> doInBackground(String... params) {
            final String sort = params[0];

            mService.loadReviews(sort, ApiUtils.API_KEY).enqueue(new Callback<MovieService.Reviews>() {
                @Override
                public void onResponse(Call<MovieService.Reviews> call, Response<MovieService.Reviews> response) {

                    if(response.isSuccessful()) {
                        for (int i = 0; i < response.body().results.size(); i++) {
                            reviewList.add(response.body().results.get(i));
                        }
                        reviewAdapter.notifyDataSetChanged();
                        if (reviewList.isEmpty()) {
                            reviewsRecyclerView.setVisibility(View.INVISIBLE);
                            noReviewView.setVisibility(View.VISIBLE);
                        }
                    }
                }

                @Override
                public void onFailure(Call<MovieService.Reviews> call, Throwable t) {
                    //showErrorMessage();
                    Toast.makeText(getContext(),"Error while loading Movies",Toast.LENGTH_LONG).show();
                    Log.d("MainActivity", "error loading from API");

                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(List<ReviewModel> movieModels) {
            super.onPostExecute(movieModels);
        }
    }

    private class FetchTrailers extends AsyncTask<String, Void,
            List<TrailerModel>> {


        @Override
        protected void onPreExecute() {

        }

        @Override
        protected List<TrailerModel> doInBackground(String... params) {
            final String sort = params[0];
            mService.loadTrailers(sort, ApiUtils.API_KEY).enqueue(new Callback<MovieService.Trailers>() {
                @Override
                public void onResponse(Call<MovieService.Trailers> call, Response<MovieService.Trailers> response) {

                    if(response.isSuccessful()) {

                    for (int i = 0; i < response.body().results.size(); i++) {
                        trailerList.add(response.body().results.get(i));
                    }
                    trailerAdapter.notifyDataSetChanged();
                    if (trailerList.isEmpty()) {
                        trailersRecyclerView.setVisibility(View.INVISIBLE);
                        noTrailerView.setVisibility(View.VISIBLE);
                    }
                }}

                    @Override
                    public void onFailure(Call<MovieService.Trailers> call, Throwable t) {
                        //showErrorMessage();
                        Toast.makeText(getContext(),"Error while loading Movies",Toast.LENGTH_LONG).show();
                        Log.d("MainActivity", "error loading from API");

                    }
                });

            return null;
        }

        @Override
        protected void onPostExecute(List<TrailerModel> movieModels) {
            super.onPostExecute(movieModels);
        }
    }

    private class isFavorite extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            Cursor cursor = getContext().getContentResolver()
                    .query(
                            MovieContract.MovieEntry.CONTENT_URI,
                            new String[]{MovieContract.MovieColumns.MOVIE_ID },
                            MovieContract.MovieColumns.MOVIE_ID + " = ?",
                            new String[]{ String.valueOf(movieModel.getId()) },
                            null
                    );
            boolean isExists = cursor != null && cursor.getCount() == 1;
            if (cursor != null) {
                cursor.close();
            }
            return isExists;
        }

        @Override
        protected void onPostExecute(Boolean isExists) {
            super.onPostExecute(isExists);
            if (isExists) {
                favButton.setImageResource(R.drawable.add_fav);
            } else {
                favButton.setImageResource(R.drawable.remove_fav);
            }
            isFavorite = isExists;
        }
    }

    private class ToggleFavorite extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            boolean isSuccessful;
            /*
            If movie is already among favorites, remove it.
            Else add.
             */
            if (isFavorite) {
                isSuccessful = getContext().getContentResolver()
                        .delete(
                                MovieContract.MovieEntry.CONTENT_URI,
                                MovieContract.MovieColumns.MOVIE_ID + " = ?",
                                new String[]{ String.valueOf(movieModel.getId()) }
                        ) == 1;
            } else {
                isSuccessful = getContext().getContentResolver()
                        .insert(MovieContract.MovieEntry.CONTENT_URI, getContentValues()) != null;
            }
            return isSuccessful;
        }

        @Override
        protected void onPostExecute(Boolean isSuccessful) {
            super.onPostExecute(isSuccessful);
            if (!isSuccessful) {
                Toast.makeText(getContext(),"Adding as Favorite NOT succesfull",Toast.LENGTH_LONG).show();
                return;
            }
            isFavorite = !isFavorite;
            if (isFavorite) {
                favButton.setImageResource(R.drawable.add_fav);
                Toast.makeText(getContext(),"Movie added as Favorite",Toast.LENGTH_LONG).show();
            } else {
                favButton.setImageResource(R.drawable.remove_fav);
                Toast.makeText(getContext(),"Movie removed from favorite",Toast.LENGTH_LONG).show();
            }
        }

        private ContentValues getContentValues() {
            ContentValues values = new ContentValues();
            values.put(MovieContract.MovieColumns.MOVIE_ID, movieModel.getId());
            values.put(MovieContract.MovieColumns.MOVIE_TITLE, movieModel.getTitle());
            values.put(MovieContract.MovieColumns.MOVIE_RELEASE_DATE, movieModel.getReleaseDate());
            values.put(MovieContract.MovieColumns.MOVIE_VOTE_AVERAGE, movieModel.getVoteAverage());
            values.put(MovieContract.MovieColumns.MOVIE_OVERVIEW, movieModel.getOverview());
            values.put(MovieContract.MovieColumns.MOVIE_POSTER_PATH, movieModel.getPosterPath());
            values.put(MovieContract.MovieColumns.MOVIE_BACKDROP_PATH, movieModel.getBackdropPath());

            System.out.println("THE VALUES ADDED ARE "+values);

            return values;
        }
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }



}
