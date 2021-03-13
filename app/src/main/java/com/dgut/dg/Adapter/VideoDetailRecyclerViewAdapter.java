package com.dgut.dg.Adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dgut.dg.Activity.HomeActivity;
import com.dgut.dg.Activity.VideoDetailsActivity;
import com.dgut.dg.Utils.VideoBean;
import com.dgut.dg.R;

import java.text.SimpleDateFormat;
import java.util.List;

import cn.jzvd.JzvdStd;


public class VideoDetailRecyclerViewAdapter extends RecyclerView.Adapter<VideoDetailRecyclerViewAdapter.MyViewHolder> {

    public static final String TAG = "AdapterRecyclerView";
    private Context context;
    List<VideoBean.ItemListBean> mDatas;



    public VideoDetailRecyclerViewAdapter(Context context, List<VideoBean.ItemListBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_videoview, parent,
                false));

        return holder;
    }



    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder [" + holder.jzvdStd.hashCode() + "] position=" + position);


        if (mDatas.size() > 0){
            Log.i(TAG, "***onBindViewHolder: 5");
            VideoBean.ItemListBean.DataBean dataBean = mDatas.get(position).getData();
            String thumbUrl = dataBean.getCover().getFeed();    // 缩略图地址

            holder.jzvdStd.setUp(dataBean.getPlayUrl(), dataBean.getTitle());
            Glide.with(holder.jzvdStd.getContext()).load(thumbUrl).into(holder.jzvdStd.thumbImageView);


            Log.i(TAG, "onBindViewHolder: -- position" + position);
            Log.i(TAG, "onBindViewHolder: --" + dataBean.getPlayUrl());

            // 设置时间
            long before = dataBean.getDate();
            String result = new SimpleDateFormat("HH").format(before);
            holder.tvTime.setText(Integer.parseInt(result)+"h");

            // 设置图片
            holder.igInto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    JzvdStd.releaseAllVideos();
                    Intent intent = new Intent(context, VideoDetailsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("playUrl", dataBean.getPlayUrl());
                    bundle.putString("thumbUrl", thumbUrl);
                    bundle.putString("title", dataBean.getTitle());
                    intent.putExtras(bundle);

                    context.startActivity(intent);
                }
            });


        }else {
            Log.i(TAG, "onBindViewHolder: 数据为空");
        }



    }



    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        JzvdStd jzvdStd;

        TextView tvTime;
        ImageView igInto;

        public MyViewHolder(View itemView) {
            super(itemView);
            jzvdStd = itemView.findViewById(R.id.videoplayer);
            tvTime = itemView.findViewById(R.id.tv_time);
            igInto = itemView.findViewById(R.id.imageView);

        }
    }


}
