package com.dgut.dg.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dgut.dg.Adapter.NewsDetailRecyclerViewAdapter;
import com.dgut.dg.R;
import com.dgut.dg.Utils.HttpUtils;
import com.dgut.dg.Utils.NewsBean;
import com.dgut.dg.Application.MyApplication;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class NewsFragment extends Fragment {

    RecyclerView recyclerView;
    List<NewsBean.ResultDTO.DataDTO> mDatas;
    NewsDetailRecyclerViewAdapter adapter;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            if (msg.what == 0x111){

                mDatas.clear();
                String json = (String) msg.obj;
                NewsBean newsBean = new Gson().fromJson(json, NewsBean.class);

                if(newsBean != null){
                    NewsBean.ResultDTO result = newsBean.getResult();
                    List<NewsBean.ResultDTO.DataDTO> data = result.getData();

                    for (int i=0; i<data.size(); i++){
                        mDatas.add(data.get(i));
                    }
                }

                adapter.notifyDataSetChanged();

                }
            }


    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 配置信息，切勿外传
        String url = new MyApplication().getUrl();

        mDatas = new ArrayList<>();
        loadData(url);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news, container, false);

        adapter = new NewsDetailRecyclerViewAdapter(getActivity(), mDatas);


        recyclerView = view.findViewById(R.id.rv_news);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);


        return view;
    }

    private void loadData(String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String json = HttpUtils.getJsonContent(url);

                Message message = new Message();
                message.what = 0x111;
                message.obj = json;
                handler.sendMessage(message);


            }
        }).start();

    }
}