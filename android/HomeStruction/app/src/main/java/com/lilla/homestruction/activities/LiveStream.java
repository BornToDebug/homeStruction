package com.lilla.homestruction.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.Toast;

import com.lilla.homestruction.R;

import org.videolan.libvlc.media.VideoView;

/**
 * Created by lilla on 17/10/16.
 */

public class LiveStream extends AppCompatActivity {

    VideoView videoView;
    private String path = "rtmp://homestruction.servebeer.com/live/";
    private Uri uri;
    private Intent intent;
    private WebView webView;
    MediaController mediaController;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_stream);




        videoView = (VideoView) findViewById(R.id.player);
        mediaController = new MediaController(this);

        videoView.setVideoURI(uri);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.start();
    }


//        if (!LibsChecker.checkVitamioLibs(this)) {
//            return;
//        }
//        mVideoView = (VideoView) findViewById(R.id.vitamio_videoView);
//        path = "rtmp://homestruction.servebeer.com/live/";
//        /*options = new HashMap<>();
//        options.put("rtmp_playpath", "");
//        options.put("rtmp_swfurl", "");
//        options.put("rtmp_live", "1");
//        options.put("rtmp_pageurl", "");*/
//        mVideoView.setVideoPath(path);
//        //mVideoView.setVideoURI(Uri.parse(path), options);
//        mVideoView.setMediaController(new MediaController(this));
//        mVideoView.requestFocus();
//
//        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mediaPlayer) {
//                mediaPlayer.setPlaybackSpeed(1.0f);
//            }
//        });

}
