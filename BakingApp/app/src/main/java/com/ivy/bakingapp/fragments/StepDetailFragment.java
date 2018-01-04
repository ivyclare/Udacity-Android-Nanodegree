package com.ivy.bakingapp.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaButtonReceiver;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.ivy.bakingapp.R;
import com.squareup.picasso.Picasso;
public class StepDetailFragment extends Fragment {

    //exoplayer view
    private SimpleExoPlayerView mExoPlayerView;

    //media session
    private static MediaSessionCompat mMediaSession;

    //media state builder
    private PlaybackStateCompat.Builder mStateBuilder;

    private SimpleExoPlayer mExoPlayer;
    private TextView textView;
    private ImageView img_thumbnail;

    public static final String VIDEO_URL = "video_url";
    public static final String STEP_DETAILS  = "step_details";
    public static final String IS_lAND_SCAPE = "is_landscape";
    public static final String THUMBNAIL = "thumbnail";

    private final String STATE_RESUME_POSITION = "resumePosition";

    private long mResumePosition=0;

    public StepDetailFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.exo_player_view, container, false);
        mExoPlayerView = (SimpleExoPlayerView) rootView.findViewById(R.id.playerView);
        textView = (TextView) rootView.findViewById(R.id.stepDetails);
        img_thumbnail = (ImageView) rootView.findViewById(R.id.img_thumbnail);
        img_thumbnail.setVisibility(View.GONE);

        if (savedInstanceState != null) {
            mResumePosition = savedInstanceState.getLong(STATE_RESUME_POSITION);
        }
        if(mExoPlayerView!=null){
            Bundle bundle = this.getArguments();
            if (bundle != null) {
                textView.setText(bundle.getString(STEP_DETAILS));
                String url = bundle.getString(VIDEO_URL);

                if(url!=null &&url.length()>10) {
                    setUpExoPlayer(url);
                }else{
                    Toast.makeText(getActivity(),getString(R.string.novideo), Toast.LENGTH_SHORT).show();
                    //check if there is a thumbnail and show
                    mExoPlayerView.setVisibility(View.GONE);
                    String thumbnail= bundle.getString(THUMBNAIL);
                    if(thumbnail!=null &&thumbnail.length()>10){
                        //there is a picture, display it
                        img_thumbnail.setVisibility(View.VISIBLE);

                        //load image or thumbnail depending on the one that is available.
                        Picasso.with(getContext()).load(thumbnail).fit()
                                .error(android.R.drawable.ic_menu_slideshow)
                                .placeholder(android.R.drawable.ic_menu_slideshow)
                                .into(img_thumbnail);


                    }else{
                        img_thumbnail.setVisibility(View.VISIBLE);
                    }
                }
            }

        }

        return rootView;
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {

        if(mExoPlayer!=null) {
            mResumePosition = Math.max(0, mExoPlayer.getCurrentPosition());
            outState.putLong(STATE_RESUME_POSITION, mResumePosition);
            super.onSaveInstanceState(outState);
        }
    }


    @Override
    public void onStop() {
        super.onStop();
        //release player here as per previous review
        releasePlayer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }



    @Override
    public void onStart() {
        super.onStart();


    }


    private void setUpExoPlayer(String urlString) {

        Handler mainHandler = new Handler();
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);

        mExoPlayerView.setVisibility(View.VISIBLE);
        mExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector);
        mExoPlayerView.setPlayer(mExoPlayer);
        DefaultBandwidthMeter bandwidthMeter1 = new DefaultBandwidthMeter();
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getActivity(),
                Util.getUserAgent(getActivity(), getString(R.string.app_name)), bandwidthMeter1);
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        MediaSource videoSource = new ExtractorMediaSource(Uri.parse(urlString),
                dataSourceFactory, extractorsFactory, mainHandler, null);
        mExoPlayer.prepare(videoSource);

        if (mResumePosition != 0) {
            mExoPlayer.seekTo(mResumePosition);
            //Toast.makeText(getActivity(),"seek "+mResumePosition, Toast.LENGTH_SHORT).show();
        }

        mExoPlayer.setPlayWhenReady(true);

    }


    /**
     * Broadcast Receiver registered to receive the MEDIA_BUTTON intent coming from clients.
     */
    public static class MediaReceiver extends BroadcastReceiver {

        public MediaReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            MediaButtonReceiver.handleIntent(mMediaSession, intent);
        }
    }

    /**
     * Release ExoPlayer.
     */
    private void releasePlayer() {
        if(mExoPlayer!=null) {
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }




}

