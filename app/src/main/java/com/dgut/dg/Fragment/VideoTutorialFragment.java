package com.dgut.dg.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dgut.dg.Adapter.VideoDetailRecyclerViewAdapter;
import com.dgut.dg.R;
import com.dgut.dg.Utils.HttpUtils;
import com.dgut.dg.Utils.VideoBean;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.Jzvd;

import static android.content.ContentValues.TAG;


public class VideoTutorialFragment extends Fragment {


    RecyclerView recyclerView;
    VideoDetailRecyclerViewAdapter adapter;

    List<VideoBean.ItemListBean> mDatas;


    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);


            if (msg.what == 0x123){
                String json = (String) msg.obj;

                // 解析数据
                VideoBean videoBean = new Gson().fromJson(json, VideoBean.class);

                // 避免网络问题，导致获取不到数据而报错
                if (videoBean != null){

                    // 过滤数据
                    List<VideoBean.ItemListBean> itemList = videoBean.getItemList();


                    for (int i = 0; i <itemList.size() ; i++) {
                        VideoBean.ItemListBean listBean = itemList.get(i);
                        if(listBean != null){
                            if (listBean.getType().equals("video")) {
                                mDatas.add(listBean);

                            }
                        }

                    }
                }else {
                    Log.i(TAG, "handleMessage: 获取数据失败！");
                }


                // 提示适配器更新数据
                adapter.notifyDataSetChanged();
            }
        }
    };



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        String url = "http://baobab.kaiyanapp.com/api/v4/tabs/selected?date=1614733200000&num=2&page=1";
        mDatas = new ArrayList<>();

        loadData(url);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_tutorial, container, false);


        adapter = new VideoDetailRecyclerViewAdapter(getActivity(), mDatas);


        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        recyclerView.setAdapter(adapter);


        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {

            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                Jzvd jzvd = view.findViewById(R.id.videoplayer);
                if (jzvd != null && Jzvd.CURRENT_JZVD != null &&
                        jzvd.jzDataSource.containsTheUrl(Jzvd.CURRENT_JZVD.jzDataSource.getCurrentUrl())) {
                    if (Jzvd.CURRENT_JZVD != null && Jzvd.CURRENT_JZVD.screen != Jzvd.SCREEN_FULLSCREEN) {
                        Jzvd.releaseAllVideos();
                    }
                }
            }
        });





        return view;
    }

    private void loadData(String url) {
        // 创建新线程，完成数据的获取
        new Thread(new Runnable() {
            @Override
            public void run() {


                String jsonContent = HttpUtils.getJsonContent(url);


                // 子线程不能更新UI， 需要Handler发送数据到主线程
                Message message = new Message();   // 发送的消息对象
                message.what = 0x123;
                message.obj = jsonContent;
                handler.sendMessage(message);
                Log.i(TAG, "***run: loadData 1");


            }
        }).start();
    }


    // 缺方法





}