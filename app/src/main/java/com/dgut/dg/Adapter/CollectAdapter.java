package com.dgut.dg.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dgut.dg.R;
import com.dgut.dg.entity.NewsEntity;

import java.util.List;

public class CollectAdapter extends RecyclerView.Adapter<CollectAdapter.PersonViewHolder> {

    private Context mContext;
    List<NewsEntity> mData;

    NewsEntity curNewsEntity;

    public CollectAdapter(Context context, List<NewsEntity> data){
        this.mContext = context;
        this.mData = data;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_collect, null);

        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {

        curNewsEntity = mData.get(position);

        holder.tvTitle.setText(curNewsEntity.getTitle());
        Glide.with(holder.ivThumb.getContext()).load(curNewsEntity.getThumbUrl()).into(holder.ivThumb);


        holder.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(mContext, "你确定要删除吗", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }



    class PersonViewHolder extends RecyclerView.ViewHolder
    {
        Button btnDel;
        ImageView ivThumb;
        TextView tvTitle;

        public PersonViewHolder(View itemView) {
            super(itemView);

            btnDel = itemView.findViewById(R.id.btn_del);
            ivThumb = itemView.findViewById(R.id.iv_thumb);
            tvTitle = itemView.findViewById(R.id.tv_title);

        }


    }

}
