package com.lilla.homestruction.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lilla.homestruction.R;
import com.lilla.homestruction.interfaces.WebService;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by lilla on 17/10/16.
 */

public class LiveStream extends AppCompatActivity {

    VideoView mVideoView;
    private String path;

    protected void onCreate(Bundle savedInstanceState) {
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
}