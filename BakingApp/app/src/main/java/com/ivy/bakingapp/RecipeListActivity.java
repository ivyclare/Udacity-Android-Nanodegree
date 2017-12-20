package com.ivy.bakingapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.ivy.bakingapp.Listeners.RecyclerClickListener;
import com.ivy.bakingapp.data.model.IngredientModel;
import com.ivy.bakingapp.data.model.RecipeModel;
import com.ivy.bakingapp.data.model.StepModel;
import com.ivy.bakingapp.data.remote.ApiUtils;
import com.ivy.bakingapp.data.remote.RecipeService;
import com.ivy.bakingapp.adapters.RecipeAdapter;
import com.ivy.bakingapp.fragments.IngredientsFragment;
import com.ivy.bakingapp.fragments.StepsFragment;
import com.ivy.bakingapp.idlingresource.SimpleIdlingResource;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * An activity representing a list of Recipes. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link RecipeDetail} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class RecipeListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recipe_list)
    RecyclerView recyclerView;
    public static ProgressBar progressBar;
    GridLayoutManager mLayoutManager;

    ArrayList<RecipeModel> recipeList;
    RecipeAdapter recipeAdapter;
    private RecipeService mService;
    private boolean mTwoPane,mIsResumed;
    boolean isTablet;

    @Nullable
    private SimpleIdlingResource mIdlingResource;

    /**
     * Only called from test, creates and returns a new {@link SimpleIdlingResource}.
     */
    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        ButterKnife.bind(this);

        isTablet = getResources().getBoolean(R.bool.isTablet);
        mService = ApiUtils.getRecipeService();

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        if (progressBar != null) {
            progressBar.setVisibility(View.INVISIBLE);
        }

        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        recipeList = new ArrayList<>();

        recipeAdapter = new RecipeAdapter(this, recipeList);

        if (isConnectionAvailable()) {

            (new GetRecipes()).execute();
        }

        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.recipe_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        recyclerView.addOnItemTouchListener(new RecyclerClickListener(this, new RecyclerClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                               RecipeModel recipeModel  = recipeList.get(position);
                ArrayList<StepModel> steps = recipeModel.getSteps();
                ArrayList<IngredientModel> ingredients = recipeModel.getIngredients();

                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putParcelable("recipe", recipeModel);
                    arguments.putParcelableArrayList("steps", steps);
                    arguments.putParcelableArrayList("ingredients", ingredients);

                    System.out.println("ENTERS ON ITEM TOUCH LISTENER");
                    IngredientsFragment fragment = IngredientsFragment.newInstance(recipeModel);
                    fragment.setArguments(arguments);

                    StepsFragment stepfragment = new StepsFragment();
                    stepfragment.setArguments(arguments);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.recipe_detail_container, fragment)
                            .commit();
                    } else {
                        Intent intents = new Intent(getApplicationContext(), RecipeDetail.class);
                        intents.putExtra("recipe", recipeModel);
                        startActivity(intents);
                    }

            }
        }));
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {

        mLayoutManager = new GridLayoutManager(this, 1);

        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        if(isTablet){

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                mLayoutManager.setSpanCount(2);
                recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(10), true));
            } else {
                mLayoutManager.setSpanCount(1);
                recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
            }
        }else {

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                mLayoutManager.setSpanCount(1);
                recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
            } else {
                mLayoutManager.setSpanCount(2);
                recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(10), true));
            }
        }

        recyclerView.setAdapter(recipeAdapter);

        recyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public void onResume() {
        super.onResume();
        mIsResumed = true;
    }

    @Override
    public void onPause() {
        mIsResumed = false;
        super.onPause();
    }
    //Asynctask to get recipe data
    private class GetRecipes extends AsyncTask<Void, Void,
            List<RecipeModel>> {
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected List<RecipeModel> doInBackground(Void... params) {
            //final String sort = params[0];

            mService.loadRecipes().enqueue(new Callback<List<RecipeModel>>() {
                @Override
                public void onResponse(Call<List<RecipeModel>> call, Response<List<RecipeModel>> response) {

                    Log.d("****RESPONSE",response.message());

                    if(response.isSuccessful()) {
                        System.out.println("Enters Success");
                        // mAdapter.updateAnswers(response.body().getItems());
                            for (int i = 0; i < response.body().size(); i++) {
                                recipeList.add(response.body().get(i));
                            }
                            recipeAdapter.notifyDataSetChanged();
                        if (mTwoPane && recipeList.size()!=0) {

                            RecipeModel recipeModel  = recipeList.get(0);
                            Bundle arguments = new Bundle();
                            arguments.putParcelable("recipe", recipeList.get(0));

                            ArrayList<StepModel> steps = recipeModel.getSteps();
                            ArrayList<IngredientModel> ingredients = recipeModel.getIngredients();


                            arguments.putParcelable("recipe", recipeModel);
                            arguments.putParcelableArrayList("steps", steps);
                            arguments.putParcelableArrayList("ingredients", ingredients);

                            IngredientsFragment fragment = IngredientsFragment.newInstance(recipeModel);
                            fragment.setArguments(arguments);

                            StepsFragment stepfragment = new StepsFragment();
                            stepfragment.setArguments(arguments);


                            //IngredientsFragment fragment = new IngredientsFragment();
                            fragment.setArguments(arguments);
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.recipe_detail_container, fragment)
                                    .commit();

                        }

                        Log.d("MainActivity", "posts loaded from API");
                    }else {
                        int statusCode  = response.code();
                        // handle request errors depending on status code
                    }
                }

                @Override
                public void onFailure(Call<List<RecipeModel>> call, Throwable t) {
                    //showErrorMessage();
                    // Very important for debugging
                    Log.d("ERRORRRRR", t.getMessage());
                    t.printStackTrace();

                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(List<RecipeModel> recipeModels) {

        }
    }


    private boolean isConnectionAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public class NetworkReceiver extends BroadcastReceiver {
        public NetworkReceiver(){

        }
        @Override
        public void onReceive(Context context, Intent intent) {
            (new GetRecipes()).execute();
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
