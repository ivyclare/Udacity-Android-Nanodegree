package com.ivy.bakingapp.fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.ivy.bakingapp.R;
import com.ivy.bakingapp.adapters.StepAdapter;
import com.ivy.bakingapp.data.model.StepModel;
import com.ivy.bakingapp.utils.DisplayMetricUtils;
import com.ivy.bakingapp.utils.ImageUtils;
import com.ivy.bakingapp.utils.TextUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepDetailFragment extends Fragment implements ExoPlayer.EventListener {

    private static MediaSessionCompat mMediaSession;
    @BindView(R.id.short_description)
    TextView ShortDescription;
    @BindView(R.id.description)
    TextView Description;
    @BindView(R.id.player_view)
    SimpleExoPlayerView videoPlayerView;
    @BindView(R.id.progress_bar)
    ProgressBar ProgressBar;
    @BindView(R.id.ll_container)
    LinearLayout llContainer;
    private StepModel step;
    private boolean isTablet;
    private SimpleExoPlayer mExoPlayer;
    private PlaybackStateCompat.Builder mStateBuilder;
    private String videoUrl;
    private long currentVideoPosition;

    public StepDetailFragment() {
        // Required empty public constructor
    }

    public static StepDetailFragment newInstance(StepModel step, boolean isTablet) {
        StepDetailFragment stepDetailFragment = new StepDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("step", step);
        bundle.putBoolean("isTablet", isTablet);
        stepDetailFragment.setArguments(bundle);

        return stepDetailFragment;
    }

    public StepModel getCurrentStep() {
        return step;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.v("OnCreateView", "TRUE");
        View view = inflater.inflate(R.layout.fragment_step_detail, container, false);
        ButterKnife.bind(this, view);

        if (getArguments() != null) {
            step = getArguments().getParcelable("step");
            isTablet = getArguments().getBoolean("isTablet");
            if (step != null) {
                ShortDescription.setText(step.getShortDescription());
                Description.setText(step.getDescription());
                if ((step.getVideoURL() == null || step.getVideoURL().isEmpty()) &&
                        (step.getThumbnailURL() == null || step.getThumbnailURL().isEmpty())) {
                    videoPlayerView.setVisibility(View.GONE);
                } else {
                    if (isTablet) {
                        ViewGroup.LayoutParams layoutParams =
                                videoPlayerView.getLayoutParams();
                        layoutParams.width = (int) (2.0f / 3.0f *
                                DisplayMetricUtils.getDeviceWidth(getActivity()));
                        layoutParams.height = (int) (9.0f / 16.0f * layoutParams.width);
                        videoPlayerView.setLayoutParams(layoutParams);
                    }

                    if (step.getThumbnailURL() != null && step.getThumbnailURL().length() >= 3 &&
                            !TextUtils.getExtension(step.getThumbnailURL()).equalsIgnoreCase("mp4")) {

                        Glide.with(this)
                                .load(step.getThumbnailURL())
                                .listener(new RequestListener<Drawable>() {
                                    @Override
                                    public boolean onLoadFailed(@Nullable GlideException e,
                                                                Object model, Target<Drawable> target,
                                                                boolean isFirstResource) {
                                        return false;
                                    }

                                    @Override
                                    public boolean onResourceReady(Drawable resource, Object model,
                                                                   Target<Drawable> target,
                                                                   DataSource dataSource,
                                                                   boolean isFirstResource) {
                                        videoPlayerView.setDefaultArtwork(
                                                ImageUtils.drawableToBitmap(resource));
                                        return true;
                                    }
                                });
                    }

                    if (step.getVideoURL() != null && !step.getVideoURL().equalsIgnoreCase("")) {
                        videoUrl = step.getVideoURL();
                        initializePlayer();
                    } else {
                        videoPlayerView.setVisibility(View.GONE);
                    }
                }
            }
        }

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
       // currentVideoPosition = mExoPlayer.getCurrentPosition();
        releasePlayer();
    }

    @Override
    public void onResume() {
        super.onResume();
        initializePlayer();
    }

    private void initializePlayer() {
        initializeMediaSession();
        initializeVideoPlayer();
    }

    private void initializeMediaSession() {
        if (mMediaSession == null) {
            mMediaSession = new MediaSessionCompat(getActivity(), "Recipe");
            mMediaSession.setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
                    MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);
            mMediaSession.setMediaButtonReceiver(null);

            mStateBuilder = new PlaybackStateCompat.Builder().setActions(
                    PlaybackStateCompat.ACTION_PLAY |
                            PlaybackStateCompat.ACTION_PAUSE |
                            PlaybackStateCompat.ACTION_PLAY_PAUSE);

            mMediaSession.setPlaybackState(mStateBuilder.build());

            mMediaSession.setCallback(new MediaSessionCompat.Callback() {
                @Override
                public void onPlay() {
                    mExoPlayer.setPlayWhenReady(true);
                }

                @Override
                public void onPause() {
                    mExoPlayer.setPlayWhenReady(false);
                }

                @Override
                public void onSkipToPrevious() {
                    mExoPlayer.seekTo(0);
                }
            });

            mMediaSession.setActive(true);
        }
    }

    private void initializeVideoPlayer() {
        if (mExoPlayer == null && videoUrl != null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector, loadControl);
            mExoPlayer.seekTo(currentVideoPosition);
            videoPlayerView.setPlayer(mExoPlayer);

            mExoPlayer.addListener(this);

            String userAgent = Util.getUserAgent(getActivity(), "Recipe");
            MediaSource mediaSource = new ExtractorMediaSource(Uri.parse(videoUrl), new DefaultDataSourceFactory(
                    getActivity(), userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
        }
    }

    private void releasePlayer() {
        if (mExoPlayer != null) {
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
        if (mMediaSession != null) {
            mMediaSession.setActive(false);
        }
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

        if (mStateBuilder != null) {
            mMediaSession.setPlaybackState(mStateBuilder.build());
        }
    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {
        showError(getString(R.string.no_internet));
    }

    @Override
    public void onPositionDiscontinuity() {

    }

    public void showError(String message) {
        Snackbar snackbar = Snackbar
                .make(llContainer, message, Snackbar.LENGTH_LONG);

        snackbar.setActionTextColor(ContextCompat.getColor(getActivity(), R.color.red_700));

        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.red_100));
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(getActivity(), R.color.red_700));
        snackbar.show();
    }

    public void setOrientation(int orientation) {
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ViewGroup.LayoutParams layoutParams =
                    videoPlayerView.getLayoutParams();
            layoutParams.width = DisplayMetricUtils.getDeviceWidth(getActivity());
            layoutParams.height = DisplayMetricUtils.getDeviceHeight(getActivity());
            videoPlayerView.setLayoutParams(layoutParams);

            Description.setVisibility(View.GONE);
            ShortDescription.setVisibility(View.GONE);
        } else {
            ViewGroup.LayoutParams layoutParams =
                    videoPlayerView.getLayoutParams();
            layoutParams.width = DisplayMetricUtils.getDeviceWidth(getActivity());
            layoutParams.height = (int) (9.0f / 16.0f * layoutParams.width);
            videoPlayerView.setLayoutParams(layoutParams);

            Description.setVisibility(View.VISIBLE);
            ShortDescription.setVisibility(View.VISIBLE);
        }
    }

}
