package com.ivy.bakingapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ivy.bakingapp.fragments.StepDetailFragment;

import com.ivy.bakingapp.Listeners.RecyclerClickListener;
import com.ivy.bakingapp.adapters.StepAdapter;
import com.ivy.bakingapp.data.model.RecipeModel;
import com.ivy.bakingapp.data.model.StepModel;
import com.ivy.bakingapp.utils.DisplayMetricUtils;
import com.ivy.bakingapp.utils.RecyclerViewMarginDecoration;

import java.util.ArrayList;


/**
 * An activity representing a list of Steps. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link StepDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class StepListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private RecipeModel recipe;
    RecyclerView recyclerView;
    StepAdapter StepAdapter;
    ArrayList<StepModel> stepModel;
    private int currentStepIndex;
    private boolean isLandScape=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkScreenOrientation();
        setContentView(R.layout.activity_step_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        stepModel = new ArrayList<StepModel>();

        if (getIntent() != null && getIntent().hasExtra("recipe")) {
            recipe = getIntent().getParcelableExtra("recipe");
        }

        recyclerView = (RecyclerView) findViewById(R.id.step_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.step_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        recyclerView.addOnItemTouchListener(new RecyclerClickListener(this, new RecyclerClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                stepModel = recipe.getSteps();
                StepModel steps = stepModel.get(position);
                currentStepIndex = stepModel.indexOf(steps);

                if (mTwoPane) {
                    System.out.println("STEP LIST ACTIVITY: TABLET ENTERS HERE");
                    StepDetailFragment fragment = new StepDetailFragment();
                    Bundle arguments = new Bundle();
//                        arguments.putParcelableArrayList("stepModel", stepModel);
//                        arguments.putParcelable("steps", steps);
//                        arguments.putInt("currentStepIndex", currentStepIndex);

                    arguments.putString(fragment.VIDEO_URL, steps.getVideoURL());
                    arguments.putString(fragment.STEP_DETAILS, steps.getDescription());
                    arguments.putBoolean(fragment.IS_lAND_SCAPE, isLandScape);

                    fragment.setArguments(arguments);

                    for(int i=0; i<stepModel.size();i++) {
                        System.out.println("THE STEPS LIST IN STEP LIST: "+ stepModel.get(i));
                    }

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.step_detail_container, fragment)
                                .commit();
                    } else {

                        System.out.println("STEP LIST ACTIVITY");
                        Intent intents = new Intent(getApplicationContext(), StepDetailActivity.class);
                        intents.putParcelableArrayListExtra("stepModel", stepModel);
                        intents.putExtra("steps", steps);
                        intents.putExtra("currentStepIndex", currentStepIndex);
                        startActivity(intents);
                    }
            }
        }));

    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        //recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(DummyContent.ITEMS));

        int column = 1;
        int marginInPixel = DisplayMetricUtils.convertDpToPixel(8);

        RecyclerViewMarginDecoration decoration =
                new RecyclerViewMarginDecoration(RecyclerViewMarginDecoration.ORIENTATION_VERTICAL,
                        marginInPixel, column);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(decoration);
        ArrayList<StepModel> steps = recipe.getSteps();

         StepAdapter = new StepAdapter(getApplicationContext(),steps);

        recyclerView.setAdapter(StepAdapter);
    }

    private void checkScreenOrientation(){
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            isLandScape=true;
            requestFullScreen();
        }
    }

    /**
     * Request for fullscreen when in landscape
     */
    private void requestFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

}
