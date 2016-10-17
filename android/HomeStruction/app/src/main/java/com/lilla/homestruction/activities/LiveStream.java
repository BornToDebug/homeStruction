package com.lilla.homestruction.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lilla.homestruction.R;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by lilla on 17/10/16.
 */

public class LiveStream extends AppCompatActivity {

    private static final String TAG = "LiveStream";
    private String path;
    //private HashMap<String, String> options;
    private VideoView mVideoView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_stream);
        if (!LibsChecker.checkVitamioLibs(this)) {
            return;
        }
        mVideoView = (VideoView) findViewById(R.id.vitamio_videoView);
        path = "rtmp://homestruction.servebeer.com/live/";
        /*options = new HashMap<>();
        options.put("rtmp_playpath", "");
        options.put("rtmp_swfurl", "");
        options.put("rtmp_live", "1");
        options.put("rtmp_pageurl", "");*/
        mVideoView.setVideoPath(path);
        //mVideoView.setVideoURI(Uri.parse(path), options);
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
