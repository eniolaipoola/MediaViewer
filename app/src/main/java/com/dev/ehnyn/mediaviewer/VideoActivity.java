package com.dev.ehnyn.mediaviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;

public class VideoActivity extends AppCompatActivity  implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener  {
    private int currentPosition = -1;
    private boolean isPlaying = false;

    private static final String DURATION = "duration";
    private static final String CURRENT_PLAYING = "isPlaying";
    public static final String  KEY_VIDEO_URL = "url";
    private static final String LOG_TAG = "tag";

    private MediaController mediaController;

    private VideoView mVideoView;
    private View overlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        mVideoView = (VideoView)findViewById(R.id.video_view);
        overlay = findViewById(R.id.overlay);

        try {
            String videoUrl = getIntent().getStringExtra(KEY_VIDEO_URL);

            Log.d("video_url", videoUrl);

            Uri videoUri = Uri.parse(videoUrl);
            Log.d("videoUri", "i suspect this one");

            mediaController = new MediaController(this);
            mediaController.setAnchorView(findViewById(R.id.fl_c));
            mVideoView.setMediaController(mediaController);
            mVideoView.setOnCompletionListener(this);
            mVideoView.setOnPreparedListener(this);
            mVideoView.setVideoURI(videoUri);
        }catch (Exception e){
            Log.e(LOG_TAG, e.getMessage());
            e.printStackTrace();
        }
        if (savedInstanceState != null) {

            currentPosition = savedInstanceState.getInt(DURATION);
            mVideoView.seekTo(currentPosition + 10);
            isPlaying = savedInstanceState.getBoolean(CURRENT_PLAYING);

            if (!isPlaying)
                mVideoView.pause();

        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(DURATION, currentPosition);
        outState.putBoolean(CURRENT_PLAYING, isPlaying);
    }

    @Override
    protected void onPause() {
        super.onPause();
        currentPosition = mVideoView.getCurrentPosition();
        isPlaying = mVideoView.isPlaying();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        finish();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {}

    @Override
    public void onPrepared(MediaPlayer mp) {
        mVideoView.start();
        Log.e(LOG_TAG, "onPrepared");
    }

    @Override
    public void onBackPressed() {
        if (mVideoView.isPlaying()) {
            mVideoView.stopPlayback();
        }
        super.onBackPressed();
    }
}
