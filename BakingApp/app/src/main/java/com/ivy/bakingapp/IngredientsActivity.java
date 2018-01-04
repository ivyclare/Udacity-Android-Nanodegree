package com.ivy.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

//                if(isTablet){
//
//                    StepDetailFragment fragment = new StepDetailFragment();
//                    Bundle bundle = new Bundle();
//                    bundle.putString(fragment.VIDEO_URL, StepModel.getVideoURL());
//                    bundle.putString(fragment.STEP_DETAILS, StepModel.getDescription());
//                    bundle.putBoolean(fragment.IS_lAND_SCAPE, isLandScape);
//                    fragment.setArguments(bundle);
//                    getSupportFragmentManager().beginTransaction()
//                            .add(R.id.step_detail_container, fragment)
//                            .commit();
//                }else {
                    Intent intents = new Intent(getApplicationContext(), StepListActivity.class);
                    intents.putExtra("recipe", recipeModel);
                    startActivity(intents);
               // }
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

       // ArrayList<IngredientModel> ingredients = getArguments().getParcelableArrayList("ingredients");

        IngredientAdapter = new IngredientAdapter(getApplicationContext(), ingredients);

        IngredientRecyclerView.setAdapter(IngredientAdapter);
    }
}