package com.dgut.dg.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dgut.dg.Activity.ShopDetailsActivity;
import com.dgut.dg.R;
import com.dgut.dg.Utils.ScreenUtils;
import com.dgut.dg.entity.GoodsInfo;


public class ShopDetailRecyclerViewAdapter extends RecyclerView.Adapter<ShopDetailRecyclerViewAdapter.LinearViewHolder> {

    public static final String TAG = "AdapterRecyclerView";
    private Context mContext;
    GoodsInfo goodsInfo[];


    public ShopDetailRecyclerViewAdapter(Context context) {

        this.mContext = context;
        goodsInfo = new GoodsInfo().getGoodsInfo(mContext);

    }


    @NonNull
    @Override
    public LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_shopview, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull LinearViewHolder holder, int position) {


        int pos = position % goodsInfo.length;
        holder.mGoodsPrice.setText(String.valueOf(goodsInfo[pos].getPrice()));
        holder.mGoodsDecs.setText(goodsInfo[pos].getDesc());


        ViewGroup.LayoutParams layoutParams = holder.mIv_shop.getLayoutParams();

        float itemWidth = (ScreenUtils.getScreenWidth(mContext) - 16*3) / 2;

        layoutParams.width = (int) itemWidth;


        BitmapFactory.Options options = new BitmapFactory.Options();
        BitmapFactory.decodeResource(mContext.getResources(),goodsInfo[pos].getGoodsImg(),options);

        //获取图片的宽高
        int width = options.outWidth;
        int height = options.outHeight;

        float scale = (itemWidth+0f)/ width;

        layoutParams.height= (int) (height*scale);

        holder.mIv_shop.setLayoutParams(layoutParams);


        Glide.with(mContext).
                load(goodsInfo[pos].getGoodsImg()).
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
        return goodsInfo.length ;
    }

    class LinearViewHolder extends RecyclerView.ViewHolder{


        private ImageView mIv_shop;
        private TextView mGoodsDecs;
        private TextView mGoodsPrice;


        public LinearViewHolder(View itemView) {
            super(itemView);

            mIv_shop = itemView.findViewById(R.id.iv_shop);
            mGoodsDecs = itemView.findViewById(R.id.goods_decs);
            mGoodsPrice = itemView.findViewById(R.id.goods_price);

        }
    }
}
