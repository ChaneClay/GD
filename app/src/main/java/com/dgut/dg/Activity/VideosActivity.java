package com.dgut.dg.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.dgut.dg.R;

public class VideosActivity extends AppCompatActivity {

    private  VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);


        String videoUrl = "https://media.w3.org/2010/05/sintel/trailer.mp4";
        Uri uri = Uri.parse(videoUrl);
        videoView  = this.findViewById(R.id.videoView);
        //设置视频控制器
        videoView.setMediaController(new MediaController(this));
        //播放完成回调
        videoView.setOnCompletionListener(new MyPlayerOnCompletionListener());
    //设置视频路径
        videoView.setVideoURI(uri);
    //开始播放视频
        Button button = findViewById(R.id.button);
        button.setOnClickListener(view -> {
            videoView.start();
        });

    }

    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {
        @Override
        public void onCompletion(MediaPlayer mp) {
            if (videoView != null){
                videoView.start();
            }else {
                System.out.println("报错了。。。");
            }
        }
    }
}