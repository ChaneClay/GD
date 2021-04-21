package com.dgut.dg.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.bumptech.glide.Glide;
import com.dgut.dg.Adapter.MyAdapter;
import com.dgut.dg.R;
import com.dgut.dg.Utils.MyHorizontalView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.jzvd.JZMediaInterface;
import cn.jzvd.JZMediaSystem;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

public class VideoDetailsActivity extends AppCompatActivity {

    String TAG = "VideoDetailsActivity";

    private Button mBtnSub;


    // 轮播
    private ImageView mImageView;
    private MyHorizontalView myHorizontalView;

    private List<Bitmap> bitmapList;

    private MyAdapter adapter;

    private JzvdStd mJzView;
    String playUrl;
    String thumbUrl;
    String title;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_details);

        ActionBar bar = getSupportActionBar();
        bar.setTitle("返回");
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setDisplayShowHomeEnabled(false);



        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        playUrl = bundle.getString("playUrl");
        thumbUrl = bundle.getString("thumbUrl");
        title = bundle.getString("title");

        // 轮播
        init();


//        mBtnSub = findViewById(R.id.btn_subscribe);
//        mBtnSub.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mBtnSub.setBackgroundResource(R.drawable.btn_subscribe);
//            }
//        });

//        playUrl = "https://static1.keepcdn.com/online/exercise/video/5afbabb3a29e345385f5e04a/1526443484376/B001C024.mp4";


//        JzvdStd jzvdStd = findViewById(R.id.jz_video);
//        jzvdStd.setUp(playUrl, title);
//        Glide.with(jzvdStd.getContext()).load(thumbUrl).into(jzvdStd.thumbImageView);


//        jzvdStd.startVideo();       //自动播放

    }


    private void init() {
//        mImageView = (ImageView)findViewById(R.id.imageView);

        mJzView = findViewById(R.id.cus_jz_video);

        bitmapList = new ArrayList<>(Arrays.asList(
                readBitMap(this, R.mipmap.image01),
                readBitMap(this, R.mipmap.image02),
                readBitMap(this, R.mipmap.image03),
                readBitMap(this, R.mipmap.image04),
                readBitMap(this, R.mipmap.image05),
                readBitMap(this, R.mipmap.image06),
                readBitMap(this, R.mipmap.image07),
                readBitMap(this, R.mipmap.image08)

        ));

        myHorizontalView = (MyHorizontalView)findViewById(R.id.my_horizontal);

        if (myHorizontalView == null){
            Log.i("TAG", "init: myHorizontalView is null");
        }

        adapter = new MyAdapter(this, bitmapList);

        //设置适配器
        myHorizontalView.initDatas(adapter);

        //添加滚动回调
//        myHorizontalView
//                .setCurrentImageChangeListener(new MyHorizontalView.CurrentImageChangeListener() {
//                    @Override
//                    public void onCurrentImgChanged(int position, View viewIndicator) {
//                        Log.e("==============","===============  " + position);
//
////                        mImageView.setImageBitmap(bitmapList.get(position));
//
//                        JzvdStd jzvdStd = findViewById(R.id.cus_jz_video);
//                        jzvdStd.setUp(playUrl, title);
//                        Glide.with(jzvdStd.getContext()).load(thumbUrl).into(jzvdStd.thumbImageView);
//                        jzvdStd.startVideo();       //自动播放
//
//
//
//                        viewIndicator.setBackgroundColor(Color.parseColor("#AA024DA4"));
//                    }
//                });

        //添加点击回调
        myHorizontalView.setOnItemClickListener(new MyHorizontalView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

//                mImageView.setImageBitmap(bitmapList.get(position));

                Log.i(TAG, "onItemClick: ---------------------");

                Log.i(TAG, "onItemClick: playUrl " + playUrl);
                Log.i(TAG, "onItemClick: title " + title);
                Log.i(TAG, "onItemClick: thumbUrl " + thumbUrl);

                Log.i(TAG, "onItemClick: ---------------------");

                view.setBackgroundColor(Color.parseColor("#AA024DA4"));
                JzvdStd jzvdStd = findViewById(R.id.cus_jz_video);
                jzvdStd.setUp(playUrl, title);
                Glide.with(jzvdStd.getContext()).load(thumbUrl).into(jzvdStd.thumbImageView);
                jzvdStd.startVideo();       //自动播放

            }
        });
    }

    public static Bitmap readBitMap(Context mContext, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;

        InputStream is = mContext.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }


    // 页面返回键
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
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