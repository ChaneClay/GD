package com.dgut.dg.Activity;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dgut.dg.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import cn.jzvd.JzvdStd;

public class VideoAdapter extends BaseAdapter {

    Context context;

    public VideoAdapter(Context context, List<VideoBean.ItemListBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    List<VideoBean.ItemListBean> mDatas;

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_mainlv, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        // 获取指定位置的数据源
        VideoBean.ItemListBean.DataBean dataBean = mDatas.get(i).getData();

        // 获取发布者的信息
        VideoBean.ItemListBean.DataBean.AuthorBean author = dataBean.getAuthor();
        holder.nameTv.setText(author.getName());
        holder.descTv.setText(author.getDescription());
        String iconURL = author.getIcon();
        if(!TextUtils.isEmpty(iconURL)){
            Picasso.with(context).load(iconURL).into(holder.iconIv);
        }

        // 获取点赞数和评论数
        VideoBean.ItemListBean.DataBean.ConsumptionBean consumptionBean = dataBean.getConsumption();
        holder.heartTv.setText(consumptionBean.getCollectionCount()+"");
        holder.replyTv.setText(consumptionBean.getReplyCount()+"");

        // 设置视频的播放
        holder.jzvdStd.setUp(dataBean.getPlayUrl(), dataBean.getTitle(), JzvdStd.SYSTEM_UI_FLAG_FULLSCREEN);
        String thumbUrl = dataBean.getCover().getFeed();    // 缩略图地址
        Picasso.with(context).load(thumbUrl).into(holder.jzvdStd.thumbImageView);

        holder.jzvdStd.positionInList = i;



        return view;
    }


    class ViewHolder{
        JzvdStd jzvdStd;
        ImageView iconIv;
        TextView nameTv, descTv, heartTv, replyTv;
        public ViewHolder(View view){
            jzvdStd = view.findViewById(R.id.item_main_jzvd);
            iconIv = view.findViewById(R.id.item_main_iv);
            nameTv = view.findViewById(R.id.item_main_tv_name);
            descTv = view.findViewById(R.id.item_main_tv_des);
            heartTv = view.findViewById(R.id.item_main_iv_heart);
            replyTv = view.findViewById(R.id.item_main_iv_reply);

        }
    }


}
