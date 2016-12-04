package com.lilla.homestruction.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lilla.homestruction.R;
import com.lilla.homestruction.interfaces.WebService;
import com.lilla.homestruction.managers.RetrofitManager;
import com.lilla.homestruction.preferences.SaveSharedPreference;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by lilla on 17/10/16.
 */

public class LiveStream extends AppCompatActivity {

    VideoView mVideoView;
    WebService webService;
    private String path;

    protected void onCreate(Bundle savedInstanceState) {
        webService = RetrofitManager.createService(WebService.class, "Token " + SaveSharedPreference.getToken(LiveStream.this));
        webService.startStream();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_stream);
        mVideoView = (VideoView) findViewById(R.id.vitamio_videoView);
        path = "rtmp://homestruction.org/live/";

        if (!LibsChecker.checkVitamioLibs(this)) {
            return;
        }

        mVideoView.setVideoPath(path);
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.requestFocus();

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setPlaybackSpeed(1.0f);
            }
        });
    }

    @Override
    protected void onStart() {
        webService.startStream();
    }

    @Override
    public void onBackPressed() {
        webService.stopStream();
    }

    @Override
    protected void onStop() {
        webService.stopStream();
    }
}