package com.dgut.dg.Adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dgut.dg.Activity.VideoDetailsActivity;
import com.dgut.dg.entity.VideoBean;
import com.dgut.dg.R;

import java.text.SimpleDateFormat;
import java.util.List;

import cn.jzvd.JzvdStd;


public class VideoDetailRecyclerViewAdapter extends RecyclerView.Adapter<VideoDetailRecyclerViewAdapter.MyViewHolder> {

    public static final String TAG = "AdapterRecyclerView";
    private Context context;
    List<VideoBean.ItemListBean> mDatas;

    private int N = 5;

    String Url[] = new String[N];
    String thumbUrl[] = new String[N];
    String title[] = new String[N];
    String time[] = new String[N];

    public VideoDetailRecyclerViewAdapter(Context context, List<VideoBean.ItemListBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_videoview, parent,
                false));




        thumbUrl[0] = "https://static1.keepcdn.com/cms_static/picture/2020/9/29/1601364201139_JUU4JUFGJUJFJUU3.png?imageMogr2/thumbnail/600x";
        thumbUrl[1] = "https://static1.keepcdn.com/cms_static/picture/2021/3/30/1617069142852_JUU2JTg4JUFBJUU1.png";
        thumbUrl[2] = "https://static1.keepcdn.com/cms_static/picture/2021/4/9/1617949630211_S2VlcEtpdCUyMCVD.jpg?imageMogr2/thumbnail/600x";
        thumbUrl[3] = "https://static1.keepcdn.com/cms_static/picture/2021/4/9/1617949814207_JUU3JTk0JUI3JUU3.jpg?imageMogr2/thumbnail/600x";
        thumbUrl[3] = "https://static1.keepcdn.com/cms_static/picture/2021/4/9/1617937740527_JUU2JUEwJUI4JUU1.jpg?imageMogr2/thumbnail/600x";
        title[0] = "燃脂拳击 K.O";
        title[1] = "拳击游戏";
        title[2] = "综合燃脂挑战跑";
        title[3] = "男生1000米特训";
        title[4] = "核心功能进阶";

        time[0] = "10min";
        time[1] = "15min";
        time[2] = "6min";
        time[3] = "9min";
        time[4] = "11min";

        return holder;
    }



    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        Log.i(TAG, "onBindViewHolder [" + holder.jzvdStd.hashCode() + "] position=" + position);


//        if (mDatas.size() > 0){
//            VideoBean.ItemListBean.DataBean dataBean = mDatas.get(position).getData();
////            String thumbUrl = dataBean.getCover().getFeed();    // 缩略图地址
//
////            holder.jzvdStd.setUp(dataBean.getPlayUrl(), dataBean.getTitle());
////            Glide.with(holder.jzvdStd.getContext()).load(thumbUrl).into(holder.jzvdStd.thumbImageView);
//
//
//
////            thumbUrl = "https://static1.gotokeep.com/picture/frame/1500881848788.jpg";
//            String url = "https://static1.keepcdn.com/chaos/0728/A031C068_main_s.mp4";
//
//            holder.jzvdStd.setUp(url, dataBean.getTitle());
//            Glide.with(holder.jzvdStd.getContext()).load(thumbUrl).into(holder.jzvdStd.thumbImageView);
//
//
//
//
//            // 设置时间
//            long before = dataBean.getDate();
//            String result = new SimpleDateFormat("HH").format(before);
//            holder.tvTime.setText(Integer.parseInt(result)+"h");
//
//            // 设置图片
//            String finalThumbUrl = thumbUrl;
//            holder.igInto.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    JzvdStd.releaseAllVideos();
//                    Intent intent = new Intent(context, VideoDetailsActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("playUrl", dataBean.getPlayUrl());
//                    bundle.putString("thumbUrl", finalThumbUrl);
//                    bundle.putString("title", dataBean.getTitle());
//                    intent.putExtras(bundle);
//
//                    context.startActivity(intent);
//                }
//            });
//
//
//        }else {
//            Log.i(TAG, "onBindViewHolder: 数据为空");
//        }
//

        // 新数据

        int curIndex = position % N;
        String url = "https://static1.keepcdn.com/chaos/0728/A031C068_main_s.mp4";
        holder.jzvdStd.setUp(url, title[curIndex]);
        Glide.with(holder.jzvdStd.getContext()).load(thumbUrl[curIndex]).into(holder.jzvdStd.thumbImageView);
        holder.tvTime.setText(time[curIndex]);




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
            igInto = itemView.findViewById(R.id.iv_head);

        }
    }


}
