package com.dgut.dg.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dgut.dg.Activity.NewsDetailsActivity;
import com.dgut.dg.Dao.NewsEntityDao;
import com.dgut.dg.R;
import com.dgut.dg.Utils.GetJsonDataUtil;
import com.dgut.dg.entity.JsonBean;
import com.dgut.dg.entity.NewsBean;
import com.dgut.dg.entity.NewsEntity;
import com.google.gson.Gson;
import com.google.gson.JsonArray;


import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;


public class NewsDetailRecyclerViewAdapter extends RecyclerView.Adapter<NewsDetailRecyclerViewAdapter.MyViewHolder> {


    public static final String TAG = "AdapterRecyclerView";
    private Context context;

//    List<NewsBean.ResultDTO.DataDTO> mDatas;

    List<NewsEntity> newsEntities;
    NewsEntity curNewsEntity;
    NewsEntityDao newsEntityDao;


//    public NewsDetailRecyclerViewAdapter(Context context, List<NewsBean.ResultDTO.DataDTO> mDatas) {
//        this.context = context;
//        this.mDatas = mDatas;
//    }

    public NewsDetailRecyclerViewAdapter(Context context, List<NewsEntity> newsEntities) {
        this.context = context;
        this.newsEntities = newsEntities;
    }




    @NonNull
    @Override
    public NewsDetailRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_newsview, parent, false ));

        newsEntityDao = new NewsEntityDao(context);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsDetailRecyclerViewAdapter.MyViewHolder holder, int position) {


        /*
        * 1.封面图地址
        * 2.新闻地址
        * 3.新闻日期
        * 4.新闻标题
        * */

        if (newsEntities.size() > 0){

            curNewsEntity = newsEntities.get(position);

            if (curNewsEntity.getIsSel() == 1){     // 已经收藏了
                holder.iv_collect.setImageResource(R.mipmap.heart_like);
            }else {
                holder.iv_collect.setImageResource(R.mipmap.heart_default);
            }

            NewsEntity entity = newsEntities.get(position);
            String thumbUrl = entity.getThumbUrl();
            String url = entity.getUrl();
            String date = entity.getDate().replace("时间：", "");
            String title = entity.getTitle();


            holder.tv_news.setText(title);
            Glide.with(holder.iv_news.getContext()).load(thumbUrl).into(holder.iv_news);
            holder.tv_time.setText(date);

            holder.iv_collect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ContentValues values = new ContentValues();

                    if (curNewsEntity.getIsSel() == 1){
                        holder.iv_collect.setImageResource(R.mipmap.heart_default);

                        values.put("isSel", "0");
                    }else {
                        holder.iv_collect.setImageResource(R.mipmap.heart_like);
                        Toast.makeText(context, "收藏成功！", Toast.LENGTH_SHORT).show();
                        values.put("isSel", "1");
                    }

                    // 直接返回更新后的对象
                    curNewsEntity = newsEntityDao.updateNewsEntity(values, String.valueOf(position));

                }
            });


            String finalDetailUrl = url;
            holder.iv_news.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =  new Intent(context, NewsDetailsActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("url", finalDetailUrl);
                    intent.putExtras(bundle);

                    context.startActivity(intent);

                }
            });
        }

    }



    @Override
    public int getItemCount() {
        return newsEntities.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_news;
        ImageView iv_news;
        TextView tv_time;
        ImageView iv_collect;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_news = itemView.findViewById(R.id.tv_news);
            iv_news = itemView.findViewById(R.id.iv_news);
            tv_time = itemView.findViewById(R.id.tv_time);
            iv_collect = itemView.findViewById(R.id.iv_collect);
        }
    }

}
