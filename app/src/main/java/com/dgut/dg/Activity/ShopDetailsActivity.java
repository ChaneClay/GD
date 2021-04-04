package com.dgut.dg.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dgut.dg.R;
import com.dgut.dg.Utils.CommonRes;

public class ShopDetailsActivity extends AppCompatActivity {


    private ImageView mIvShop;
    private Button mBtBuy;
    private Button mBtSub;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_details);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int id = Integer.parseInt(bundle.getString("id")) % CommonRes.getImages().length;


        mIvShop = findViewById(R.id.iv_shop);
        mIvShop.setImageResource(CommonRes.getImages()[id][0]);


        mBtBuy = findViewById(R.id.bt_buy);
        mBtSub = findViewById(R.id.bt_subscribe);

        mBtBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ShopDetailsActivity.this, "进入购买页面", Toast.LENGTH_LONG).show();
            }
        });

        mBtSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ShopDetailsActivity.this, "进入订阅页面", Toast.LENGTH_LONG).show();
            }
        });






    }
}
