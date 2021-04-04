package com.dgut.dg.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dgut.dg.Activity.ShopDetailsActivity;
import com.dgut.dg.R;
import com.dgut.dg.Utils.CommonRes;
import com.dgut.dg.Utils.ScreenUtils;


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


        int num = position % CommonRes.getImages().length;

        ViewGroup.LayoutParams layoutParams = holder.mIv_shop.getLayoutParams();
        float itemWidth = (ScreenUtils.getScreenWidth(mContext) - 16*3) / 2;
        layoutParams.width = (int) itemWidth;
        float scale = (itemWidth+0f)/CommonRes.getImages()[num][1];
        layoutParams.height= (int) (CommonRes.getImages()[num][2]*scale);
        holder.mIv_shop.setLayoutParams(layoutParams);



        Glide.with(mContext).
                load(CommonRes.getImages()[num][0]).
                override(layoutParams.width, layoutParams.height).
                into(holder.mIv_shop);



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



    @Override
    public int getItemCount() {
        return CommonRes.getImages().length * 2;
    }

    class LinearViewHolder extends RecyclerView.ViewHolder{


        private ImageView mIv_shop;


        public LinearViewHolder(View itemView) {
            super(itemView);

            mIv_shop = itemView.findViewById(R.id.iv_shop);

        }
    }
}
