package com.dgut.dg.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Resource;
import com.dgut.dg.R;
import com.dgut.dg.Utils.NewsBean;
import com.dgut.dg.Utils.VideoBean;
import com.google.gson.annotations.SerializedName;

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


            String url = dataDTO.getThumbnailPicS();

            holder.tv_news.setText(dataDTO.getTitle());
            Glide.with(holder.iv_news.getContext()).load(url).into(holder.iv_news);


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

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_news = itemView.findViewById(R.id.tv_news);
            iv_news = itemView.findViewById(R.id.iv_news);


        }
    }

}
