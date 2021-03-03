package com.dgut.dg.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.dgut.dg.Activity.HttpUtils;
import com.dgut.dg.Activity.Person;
import com.dgut.dg.Activity.VideoAdapter;
import com.dgut.dg.Activity.VideoBean;
import com.dgut.dg.Activity.VideosActivity;
import com.dgut.dg.R;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import cn.jzvd.JzvdStd;


public class VideoTutorialFragment extends Fragment {


//    private TextView textView;
//    private VideoView videoView;


    private RecyclerView recyclerView;
    private List<Person> personList = new ArrayList<>();

    LinearLayoutManager layoutManager = null;


    ListView mainLv;
    String url = "http://baobab.kaiyanapp.com/api/v4/tabs/selected?udid=11111&vc=168&vn=3.3.1&deviceModel=Huawei6&first_channel=eyepetizer_baidu_market&last_channel=eyepetizer_baidu_market&system_version_code=20";
    List<VideoBean.ItemListBean> mDatas;
    private VideoAdapter adapter;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x123){
                String json = (String) msg.obj;

                System.out.println("json: "+json);

                // 解析数据
                VideoBean videoBean = new Gson().fromJson(json, VideoBean.class);

                if (videoBean == null) {
                    System.out.println("videos is null");

                }

                // 过滤数据

                List<VideoBean.ItemListBean> itemList = videoBean.getItemList();

                if (itemList == null) {
                    Toast toast = Toast.makeText(getActivity(), "itemList为空", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                for (int i = 0; i <itemList.size() ; i++) {
                    VideoBean.ItemListBean listBean = itemList.get(i);
                    if (listBean.getType().equals("video")) {
                        mDatas.add(listBean);
                    }
                }

                // 提示适配器更新数据
                adapter.notifyDataSetChanged();
            }
        }
    };



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_tutorial, container, false);


        mainLv = view.findViewById(R.id.main_lv);

        // 数据源
        mDatas = new ArrayList<>();

        // 创建适配器对象

        adapter = new VideoAdapter(getContext(), mDatas);

        // 设置适配器

        mainLv.setAdapter(adapter);

        // 加载网络数据
        loadData();




        return view;
    }

    private void loadData() {
        // 创建新线程，完成数据的获取
        new Thread(new Runnable() {
            @Override
            public void run() {
                String jsonContent = HttpUtils.getJsonContent(url);

                if (jsonContent == null || jsonContent == ""){
                    System.out.println("jsonContent is null");
                    return;
                }else {
                    System.out.println("jsonContent 内容"+jsonContent+"n");
                }

                // 子线程不能更新UI， 需要Handler发送数据到主线程

                Message message = new Message();   // 发送的消息对象
                message.what = 0x123;
                message.obj = jsonContent;
                handler.sendMessage(message);


            }
        }).start();
    }

    @Override
    public void onStop() {
        super.onStop();
        JzvdStd.releaseAllVideos();  // 释放正在播放的视频
    }
}