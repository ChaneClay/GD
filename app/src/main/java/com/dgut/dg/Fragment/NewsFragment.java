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
import com.dgut.dg.Dao.NewsEntityDao;
import com.dgut.dg.R;
import com.dgut.dg.Utils.HttpUtils;
import com.dgut.dg.entity.NewsBean;
import com.dgut.dg.Application.MyApplication;
import com.dgut.dg.entity.NewsEntity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class NewsFragment extends Fragment {

    RecyclerView recyclerView;
    NewsDetailRecyclerViewAdapter adapter;

    NewsEntityDao newsEntityDao;
    ArrayList<NewsEntity> newsEntities;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 新数据
        newsEntityDao = new NewsEntityDao(getContext());
        newsEntities = (ArrayList<NewsEntity>) newsEntityDao.getNewsEntity();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news, container, false);


        adapter = new NewsDetailRecyclerViewAdapter(getActivity(), newsEntities);

        recyclerView = view.findViewById(R.id.rv_news);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);


        return view;
    }

//    private void loadData(String url) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String json = HttpUtils.getJsonContent(url);
//
//                Message message = new Message();
//                message.what = 0x111;
//                message.obj = json;
//                handler.sendMessage(message);
//
//
//            }
//        }).start();
//
//    }
}