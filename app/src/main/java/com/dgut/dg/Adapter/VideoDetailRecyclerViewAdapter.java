package com.dgut.dg.Adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dgut.dg.Utils.VideoBean;
import com.dgut.dg.R;

import java.util.List;

import cn.jzvd.JzvdStd;


public class VideoDetailRecyclerViewAdapter extends RecyclerView.Adapter<VideoDetailRecyclerViewAdapter.MyViewHolder> {

    public static final String TAG = "AdapterRecyclerView";
    int[] videoIndexs = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
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


        Log.i(TAG, "onBindViewHolder: mdatas content*" + this.mDatas);

//        if (mDatas.size() == 0){
//            holder.jzvdStd.setUp(
//                VideoConstant.videoUrls[0][0],
//                VideoConstant.videoTitles[0][0], Jzvd.SCREEN_NORMAL);
//        String thumbUrl = "http://img.kaiyanapp.com/e52343ef8b4f270bec52fb56cbaa5123.jpeg?imageMogr2/quality/60/format/jpg";
//        Glide.with(holder.jzvdStd.getContext()).load(thumbUrl).into(holder.jzvdStd.thumbImageView);
//        }else {
//
//            Log.i(TAG, "***onBindViewHolder: 5");
//            VideoBean.ItemListBean.DataBean dataBean = mDatas.get(position).getData();
//            String thumbUrl = dataBean.getCover().getFeed();    // 缩略图地址
//
//            holder.jzvdStd.setUp(dataBean.getPlayUrl(), dataBean.getTitle());
//            Glide.with(holder.jzvdStd.getContext()).load(thumbUrl).into(holder.jzvdStd.thumbImageView);
//        }


        if (mDatas.size() > 0){
            Log.i(TAG, "***onBindViewHolder: 5");
            VideoBean.ItemListBean.DataBean dataBean = mDatas.get(position).getData();
            String thumbUrl = dataBean.getCover().getFeed();    // 缩略图地址

            holder.jzvdStd.setUp(dataBean.getPlayUrl(), dataBean.getTitle());
            Glide.with(holder.jzvdStd.getContext()).load(thumbUrl).into(holder.jzvdStd.thumbImageView);
        }





    }




    @Override
    public int getItemCount() {
        return videoIndexs.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        JzvdStd jzvdStd;

        TextView nameTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            jzvdStd = itemView.findViewById(R.id.videoplayer);

        }
    }


}
