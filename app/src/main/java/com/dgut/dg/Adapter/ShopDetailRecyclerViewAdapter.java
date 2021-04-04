package com.dgut.dg.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dgut.dg.Activity.ShopDetailsActivity;
import com.dgut.dg.Activity.VideoDetailsActivity;
import com.dgut.dg.R;
import com.dgut.dg.Utils.CommonRes;
import com.dgut.dg.Utils.VideoBean;

import java.util.List;
import java.util.zip.Inflater;


public class ShopDetailRecyclerViewAdapter extends RecyclerView.Adapter<ShopDetailRecyclerViewAdapter.LinearViewHolder> {

    public static final String TAG = "AdapterRecyclerView";
    private Context mContext;



    // 这里可以传数据mDatas
    public ShopDetailRecyclerViewAdapter(Context context) {
        this.mContext = context;

    }


    @NonNull
    @Override
    public LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_shopview, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull LinearViewHolder holder, int position) {

        initData();

        // 设置图片资源
        int[] images = new int[]{R.mipmap.image1, R.mipmap.image2, R.mipmap.image3, R.mipmap.image4};

        CommonRes.setImages(images);

        int num = position % CommonRes.getImages().length;
        holder.mIv_shop.setImageResource(CommonRes.getImages()[num]);



        holder.mIv_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 这里要用mContext，而不是ShopDetailRecyclerViewAdapter
                Toast.makeText(mContext, "position: "+position, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, ShopDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", String.valueOf(position));
                intent.putExtras(bundle);
                mContext.startActivity(intent, bundle);
            }
        });






    }

    private void initData() {


    }


    @Override
    public int getItemCount() {
        return 30;
    }

    class LinearViewHolder extends RecyclerView.ViewHolder{


        private ImageView mIv_shop;


        public LinearViewHolder(View itemView) {
            super(itemView);

            mIv_shop = itemView.findViewById(R.id.iv_shop);

        }
    }
}
