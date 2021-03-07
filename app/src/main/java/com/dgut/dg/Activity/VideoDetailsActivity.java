package com.dgut.dg.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.dgut.dg.R;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

public class VideoDetailsActivity extends AppCompatActivity {

    String TAG = "VideoDetailsActivity";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_details);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String playUrl = bundle.getString("playUrl");
        String thumbUrl = bundle.getString("thumbUrl");
        String title = bundle.getString("title");

        JzvdStd.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;  //横向
        JzvdStd.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;  //纵向


        JzvdStd jzvdStd = findViewById(R.id.jz_video);
        jzvdStd.setUp(playUrl, title);
        Glide.with(jzvdStd.getContext()).load(thumbUrl).into(jzvdStd.thumbImageView);


    }


    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            Log.i(TAG, "onBackPressed: 退出");
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: 停止");
        Jzvd.releaseAllVideos();
    }
}