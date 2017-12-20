package com.ivy.bakingapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.ivy.bakingapp.adapters.RecipeAdapter;
import com.ivy.bakingapp.data.model.StepModel;
import com.ivy.bakingapp.fragments.StepDetailFragment;
import com.ivy.bakingapp.utils.DisplayMetricUtils;
import com.ivy.bakingapp.utils.ImageUtils;
import com.ivy.bakingapp.utils.TextUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StepDetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_previous_step)
    TextView tvPreviousStep;
    @BindView(R.id.tv_next_step)
    TextView tvNextStep;
    @BindView(R.id.v_horizontal)
    View vHorizontal;
    @BindView(R.id.ll_step_navigation)
    LinearLayout llStepNavigation;


    private ArrayList<StepModel> steps = new ArrayList<>();
    private StepModel step;
    private int currentStepIndex;
    private StepDetailFragment fragment;
    private Bundle savedInstanceState;
    private int orientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("Activity", "onCreate");

        this.savedInstanceState = savedInstanceState;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_step_detail);
        ButterKnife.bind(this);

        if (getIntent() != null && getIntent().hasExtra("steps") &&
                getIntent().hasExtra("currentStepIndex")) {

            //steps = getIntent().getParcelableExtra("stepModel");
            step = getIntent().getParcelableExtra("steps");

            steps = getIntent().getParcelableArrayListExtra("stepModel");
            if (savedInstanceState == null) {
                currentStepIndex = getIntent().getIntExtra("currentStepIndex", 0);
            } else {
                currentStepIndex = savedInstanceState.getInt("currentStepIndex");
            }
            setNavigationButton(currentStepIndex);
        }

        setupFragment(savedInstanceState, currentStepIndex);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (fragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, "stepDetailFragment", fragment);
        }
        outState.putInt("currentStepIndex", currentStepIndex);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        fragment.setOrientation(orientation);
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }
            llStepNavigation.setVisibility(View.GONE);
            vHorizontal.setVisibility(View.GONE);
        } else if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            llStepNavigation.setVisibility(View.VISIBLE);
            vHorizontal.setVisibility(View.VISIBLE);
        }
    }

    private void setNavigationButton(int stepIndex) {
        if (steps.size() > 1) {
            if (stepIndex == 0) {
                tvPreviousStep.setVisibility(View.INVISIBLE);
                tvNextStep.setText(steps.get(stepIndex + 1).getShortDescription());
            } else if (stepIndex == steps.size() - 1) {
                tvNextStep.setVisibility(View.INVISIBLE);
                tvPreviousStep.setText(steps.get(stepIndex - 1).getShortDescription());
            } else {
                tvNextStep.setVisibility(View.VISIBLE);
                tvPreviousStep.setVisibility(View.VISIBLE);
                tvNextStep.setText(steps.get(stepIndex + 1).getShortDescription());
                tvPreviousStep.setText(steps.get(stepIndex - 1).getShortDescription());
            }
        } else {
            tvPreviousStep.setVisibility(View.GONE);
            tvNextStep.setVisibility(View.GONE);
        }
    }

    private void setupFragment(Bundle savedInstanceState, int stepIndex) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(steps.get(stepIndex).getShortDescription());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            if (DisplayMetricUtils.getDeviceOrientation(this) == Configuration.ORIENTATION_LANDSCAPE) {
                getSupportActionBar().hide();
            }
        }

        if (savedInstanceState != null) {
            fragment = (StepDetailFragment) getSupportFragmentManager().getFragment(savedInstanceState, "stepDetailFragment");
        } else {
            fragment = StepDetailFragment.newInstance(steps.get(stepIndex), false);
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rl_container, fragment)
                .commit();
    }

    @OnClick(R.id.tv_next_step)
    public void NextStepClick() {
        currentStepIndex = currentStepIndex + 1;
        setupFragment(savedInstanceState, currentStepIndex);
        setNavigationButton(currentStepIndex);
    }

    @OnClick(R.id.tv_previous_step)
    public void PreviousStepClick() {
        currentStepIndex = currentStepIndex - 1;
        setupFragment(savedInstanceState, currentStepIndex);
        setNavigationButton(currentStepIndex);
    }

    // In previous submission said that overriding onConfigurationChanged is not accepted,
    // but i can't find the point tn the guideline which say like that.
    // I override onConfigurationChanged in order to handle layout for landscape / portrait mode,
    // since i have added orientation on android:configChanges in AndroidManifest in order to make
    // this activity not recreated on orientation changes and make video player didn't recreated
    // on orientation changes (based on example on https://github.com/google/ExoPlayer/blob/release-v2/demo/src/main/java/com/google/android/exoplayer2/demo/PlayerActivity.java).
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        orientation = newConfig.orientation;
        onWindowFocusChanged(true);
    }
}

