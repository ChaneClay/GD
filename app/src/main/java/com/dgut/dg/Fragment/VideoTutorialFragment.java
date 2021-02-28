package com.dgut.dg.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.dgut.dg.R;

import java.io.File;
import java.util.zip.Inflater;


public class VideoTutorialFragment extends Fragment {


    private TextView textView;
    private VideoView videoView;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_video_tutorial, container, false);

        textView =  view.findViewById(R.id.tv_content);
        textView.setText("hhhhhhhhhhhh");


        String videoUrl = "https://media.w3.org/2010/05/sintel/trailer.mp4";
        Uri uri = Uri.parse(videoUrl);
        videoView = view.findViewById(R.id.videoView);

//设置视频控制器
        Context context = getContext();
        videoView.setMediaController(new MediaController(context));


//设置视频路径
        videoView.setVideoURI(uri);
//开始播放视频
        Button button = view.findViewById(R.id.button);

        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.start();
            }
        });

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                videoView.start();
//            }
//        });


        return view;
    }
}