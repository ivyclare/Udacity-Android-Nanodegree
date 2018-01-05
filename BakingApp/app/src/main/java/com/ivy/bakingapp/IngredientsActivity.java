package com.ivy.bakingapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ivy.bakingapp.adapters.IngredientAdapter;
import com.ivy.bakingapp.data.model.IngredientModel;
import com.ivy.bakingapp.data.model.RecipeModel;
import com.ivy.bakingapp.data.model.StepModel;
import com.ivy.bakingapp.fragments.StepDetailFragment;
import com.ivy.bakingapp.utils.DisplayMetricUtils;
import com.ivy.bakingapp.utils.RecyclerViewMarginDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientsActivity extends AppCompatActivity {

    private IngredientAdapter IngredientAdapter;
    @BindView(R.id.ingred_recycler)
    RecyclerView IngredientRecyclerView;

    @BindView(R.id.view_directions)
    TextView ViewDirections;

    private RecipeModel recipeModel;
    ArrayList<IngredientModel> ingredients;
    boolean isTablet;
    private ArrayList<StepModel> stepModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);
        // Inflate the layout for this fragment
        ButterKnife.bind(this);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        isTablet = getResources().getBoolean(R.bool.isTablet);

        if (getIntent() != null && getIntent().hasExtra("recipe")) {
            recipeModel = getIntent().getParcelableExtra("recipe");
        }
         ingredients = recipeModel.getIngredients();

        stepModel = recipeModel.getSteps();

        setupRecyclerView();

        ViewDirections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intents = new Intent(getApplicationContext(), StepListActivity.class);
                    intents.putExtra("recipe", recipeModel);
                    startActivity(intents);
            }
        });
    }


    private void setupRecyclerView() {
        int column = 1;
        int marginInPixel = DisplayMetricUtils.convertDpToPixel(8);

        RecyclerViewMarginDecoration decoration =
                new RecyclerViewMarginDecoration(RecyclerViewMarginDecoration.ORIENTATION_VERTICAL,
                        marginInPixel, column);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        IngredientRecyclerView.setLayoutManager(layoutManager);
        IngredientRecyclerView.addItemDecoration(decoration);

        IngredientAdapter = new IngredientAdapter(getApplicationContext(), ingredients);

        IngredientRecyclerView.setAdapter(IngredientAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home: {
                onBackPressed();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}