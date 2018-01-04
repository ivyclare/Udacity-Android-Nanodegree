package com.ivy.bakingapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ivy.bakingapp.data.model.StepModel;
import com.ivy.bakingapp.fragments.StepDetailFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * An activity representing a single Step detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link StepListActivity}.
 */
public class StepDetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_previous_step)
    TextView btnPrev;
    @BindView(R.id.tv_next_step)
    TextView btnNext;
    @BindView(R.id.ll_step_navigation)
    LinearLayout llStepNavigation;

    private ArrayList<StepModel> stepModel;
    private static  int list_position;
    private boolean isLandScape=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        checkScreenOrientation();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        stepModel = getIntent().getParcelableArrayListExtra("stepModel");
        list_position = getIntent().getExtras().getInt("currentStepIndex");

        if(!isLandScape){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(stepModel.get(list_position).getShortDescription());
        }else{
            toolbar.setVisibility(View.GONE);
        }


        StepModel StepModel = stepModel.get(list_position);

        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            StepDetailFragment fragment = new StepDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putString(fragment.VIDEO_URL, StepModel.getVideoURL());
            bundle.putString(fragment.STEP_DETAILS, StepModel.getDescription());
            bundle.putBoolean(fragment.IS_lAND_SCAPE, isLandScape);
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.step_detail_container, fragment)
                    .commit();
        }
    }

    @OnClick(R.id.tv_next_step)
    public void NextStepClick() {
        final Intent intent = new Intent(this, StepDetailActivity.class);
        intent.putParcelableArrayListExtra("stepModel", stepModel);
        intent.putExtra("currentStepIndex", list_position==stepModel.size()-1?0:list_position+1);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.tv_previous_step)
    public void PreviousStepClick() {
        final Intent intent = new Intent(this, StepDetailActivity.class);
        intent.putParcelableArrayListExtra("stepModel", stepModel);
        intent.putExtra("currentStepIndex", list_position>0?list_position-1:stepModel.size()-1);
        startActivity(intent);
        finish();
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

}
