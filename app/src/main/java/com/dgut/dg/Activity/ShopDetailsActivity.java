package com.dgut.dg.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.dgut.dg.R;
import com.dgut.dg.Utils.DatabaseHelper;
import com.dgut.dg.entity.GoodsInfo;

import java.util.HashMap;
import java.util.Map;

public class ShopDetailsActivity extends AppCompatActivity {


    private ImageView mIvShop;
    private Button mBtBuy;
    private Button mBtSub;


    GoodsInfo goodsInfos[];
    GoodsInfo goodsInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_details);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        goodsInfos = new GoodsInfo().getGoodsInfo(getApplicationContext());
        mIvShop = findViewById(R.id.iv_shop);
        int id = Integer.parseInt(bundle.getString("id"));
        goodsInfo = goodsInfos[id];
        mIvShop.setImageResource(goodsInfo.getGoodsImg());

        mBtBuy = findViewById(R.id.bt_buy);
        mBtSub = findViewById(R.id.bt_subscribe);

        ActionBar bar = getSupportActionBar();
        bar.setTitle("返回");
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setDisplayShowHomeEnabled(false);


        if (goodsInfo.getIsSub() == 1){
            mBtSub.setText("已订阅");
        }else {
            mBtSub.setText("订阅");
        }


        mBtBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ShopDetailsActivity.this, "进入购买页面", Toast.LENGTH_LONG).show();
            }
        });

        // 收藏
        mBtSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentValues values = new ContentValues();
                if (goodsInfo.getIsSub() == 1){
                    mBtSub.setText("订阅");
                    values.put("isSub", "0");
                }else {
                    mBtSub.setText("已订阅");
                    values.put("isSub", "1");
                }

                // 直接返回更新后的对象
                goodsInfo = goodsInfo.setGoodsInfo(getApplicationContext(), values, String.valueOf(id));

            }
        });
    }

    // 页面返回键
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
