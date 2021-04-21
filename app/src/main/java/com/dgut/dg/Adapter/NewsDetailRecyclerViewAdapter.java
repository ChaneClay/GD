package com.dgut.dg.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dgut.dg.Activity.NewsDetailsActivity;
import com.dgut.dg.R;
import com.dgut.dg.Utils.NewsBean;


import java.util.List;

import static androidx.appcompat.content.res.AppCompatResources.getDrawable;


public class NewsDetailRecyclerViewAdapter extends RecyclerView.Adapter<NewsDetailRecyclerViewAdapter.MyViewHolder> {


    public static final String TAG = "AdapterRecyclerView";
    private Context context;

    List<NewsBean.ResultDTO.DataDTO> mDatas;


    public NewsDetailRecyclerViewAdapter(Context context, List<NewsBean.ResultDTO.DataDTO> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }


    @NonNull
    @Override
    public NewsDetailRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_newsview, parent, false ));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsDetailRecyclerViewAdapter.MyViewHolder holder, int position) {

        if (mDatas.size() > 0){
            NewsBean.ResultDTO.DataDTO dataDTO = mDatas.get(position);


            String thumbUrl = dataDTO.getThumbnailPicS();
            String detailUrl = dataDTO.getUrl();
            String date = dataDTO.getDate();



            holder.tv_news.setText(dataDTO.getTitle());
            Glide.with(holder.iv_news.getContext()).load(thumbUrl).into(holder.iv_news);
            holder.tv_time.setText(date);


            holder.iv_news.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =  new Intent(context, NewsDetailsActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("url", detailUrl);
                    intent.putExtras(bundle);

                    Log.i(TAG, "onBindViewHolder: detailUrl " + detailUrl);




                    context.startActivity(intent);

                }
            });


        }else {
            Log.i(TAG, "onBindViewHolder: Bad");
        }

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_news;
        ImageView iv_news;
        TextView tv_time;



        public MyViewHolder(View itemView) {
            super(itemView);
            tv_news = itemView.findViewById(R.id.tv_news);
            iv_news = itemView.findViewById(R.id.iv_news);
            tv_time = itemView.findViewById(R.id.tv_time);


        }
    }

}
