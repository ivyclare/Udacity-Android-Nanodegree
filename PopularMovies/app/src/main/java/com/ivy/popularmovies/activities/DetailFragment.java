package com.ivy.popularmovies.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import com.ivy.popularmovies.utils.Util;
import com.ivy.popularmovies.R;
import com.squareup.picasso.Picasso;
import com.ivy.popularmovies.adapters.ReviewAdapter;
import com.ivy.popularmovies.adapters.TrailerAdapter;
import com.ivy.popularmovies.application.App;
import com.ivy.popularmovies.models.MoviesModel;
import com.ivy.popularmovies.models.ReviewModel;
import com.ivy.popularmovies.models.TrailerModel;
import com.ivy.popularmovies.utils.API;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class DetailFragment extends Fragment {

    MoviesModel moviesModel;
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
    Realm realm = Realm.getDefaultInstance();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if (getArguments().containsKey("movie")) {

            Activity activity = this.getActivity();
            appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.collapsing_toolbar);
            if (appBarLayout != null) {
                appBarLayout.setTitle("");
           }
            moviesModel = getArguments().getParcelable("movie");
            assert moviesModel != null;
        }
        trailerList = new ArrayList<>();
        reviewList = new ArrayList<>();
        (new FetchReviews()).execute(moviesModel.getId());
        (new FetchTrailers()).execute(moviesModel.getId());
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
                if (realm.isInTransaction())
                    realm.cancelTransaction();
                if (!isFavorite()) {
                    realm.beginTransaction();
                    favButton.setImageResource(R.drawable.add_fav);

                    realm.copyToRealm(moviesModel);
                    realm.commitTransaction();

                } else {
                    realm.beginTransaction();
                    favButton.setImageResource( R.drawable.remove_fav);
                    realm.where(MoviesModel.class).contains("id", moviesModel.getId()).findFirst().deleteFromRealm();
                    realm.commitTransaction();

                }
            }
        });


        appBarLayout.setTitle(moviesModel.getTitle());
        Picasso.with(getActivity()).load(Util.IMAGE_URL+"/w342" + moviesModel.getposter_path() + "?api_key?=" + Util.API_KEY).placeholder(R.drawable.image).error(R.drawable.image).into(thumbnail);

        rates.setText(Float.toString(moviesModel.getvote_average()).concat("/10"));
        ratesBar.setMax(5);
        ratesBar.setRating(moviesModel.getvote_average() / 2f);

        overview.setText(moviesModel.getOverview());
        releaseInfo.setText("Release Date: ".concat(moviesModel.getrelease_date()));

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
                share.putExtra(Intent.EXTRA_SUBJECT, moviesModel.getTitle());
                share.putExtra(Intent.EXTRA_TEXT, "https://www.youtube.com/watch?v=".concat(trailerList.get(0).getKey()));
                startActivity(Intent.createChooser(share, "Share Trailer!"));
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    private boolean isFavorite(){

        return realm.where(MoviesModel.class).contains("id", moviesModel.getId()).findAll().size() != 0;
    }

    private class FetchReviews extends AsyncTask<String, Void,
            List<ReviewModel>> {


        @Override
        protected void onPreExecute() {

        }

        @Override
        protected List<ReviewModel> doInBackground(String... params) {
            final String sort = params[0];
            App.getMovieClient().getMovieAPI().loadReviews(sort, Util.API_KEY).enqueue(new Callback<API.Reviews>() {

                @Override
                public void onResponse(Response<API.Reviews> response, Retrofit retrofit) {

                    for (int i = 0; i < response.body().results.size(); i++) {
                        reviewList.add(response.body().results.get(i));
                    }
                    reviewAdapter.notifyDataSetChanged();
                    if (reviewList.isEmpty()) {
                        reviewsRecyclerView.setVisibility(View.INVISIBLE);
                        noReviewView.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(List<ReviewModel> moviesModels) {
            super.onPostExecute(moviesModels);
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
            App.getMovieClient().getMovieAPI().loadTrailers(sort, Util.API_KEY).enqueue(new Callback<API.Trailers>() {

                @Override
                public void onResponse(Response<API.Trailers> response, Retrofit retrofit) {

                    for (int i = 0; i < response.body().results.size(); i++) {
                        trailerList.add(response.body().results.get(i));
                    }
                    trailerAdapter.notifyDataSetChanged();
                    if (trailerList.isEmpty()) {
                        trailersRecyclerView.setVisibility(View.INVISIBLE);
                        noTrailerView.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(List<TrailerModel> moviesModels) {
            super.onPostExecute(moviesModels);
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }



}
