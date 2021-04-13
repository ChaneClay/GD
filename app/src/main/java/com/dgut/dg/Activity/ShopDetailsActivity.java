package com.dgut.dg.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ScrollingView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.dgut.dg.R;
import com.dgut.dg.entity.GoodsInfo;

import butterknife.BindView;

public class ShopDetailsActivity extends AppCompatActivity implements GradationScrollView.ScrollViewListener{


    private ImageView ivGoodDetailImg;
    private TextView tvGoodDetailShopCart;
    private TextView tvGoodDetailBuy;
    private ImageView ivGoodDetailBack;

    RelativeLayout llTitle;
    TextView tvGoodTitle;
    ImageView iv;
    ActionBar bar;


    GoodsInfo goodsInfos[];
    GoodsInfo goodsInfo;
    private int height;
    GradationScrollView scrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_details);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        goodsInfos = new GoodsInfo().getGoodsInfo(getApplicationContext());
        int id = Integer.parseInt(bundle.getString("id"));
        goodsInfo = goodsInfos[id];

        ivGoodDetailImg = findViewById(R.id.iv_good_detai_img);
        ivGoodDetailImg.setImageResource(goodsInfo.getGoodsImg());

        // 购物车
        tvGoodDetailShopCart = findViewById(R.id.tv_good_detail_shop_cart);

        if (goodsInfo.getIsSub() == 1){
            tvGoodDetailShopCart.setText("已订阅");
        }else {
            tvGoodDetailShopCart.setText("订阅");
        }

        tvGoodDetailShopCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentValues values = new ContentValues();
                if (goodsInfo.getIsSub() == 1){
                    tvGoodDetailShopCart.setText("订阅");
                    values.put("isSub", "0");
                }else {
                    tvGoodDetailShopCart.setText("已订阅");
                    values.put("isSub", "1");
                }

                // 直接返回更新后的对象
                goodsInfo = goodsInfo.setGoodsInfo(getApplicationContext(), values, String.valueOf(id));

            }
        });


        // 购买
        tvGoodDetailBuy = findViewById(R.id.tv_good_detail_buy);
        tvGoodDetailBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ShopDetailsActivity.this, "进入购买页面", Toast.LENGTH_LONG).show();
            }
        });



        bar = getSupportActionBar();
        bar.hide();

        ivGoodDetailBack = findViewById(R.id.iv_good_detail_back);
        ivGoodDetailBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });



        llTitle = findViewById(R.id.llTitle);
        iv = findViewById(R.id.iv_good_detai_img);
        initListeners();



    }



    private void initListeners() {


        ViewTreeObserver vto = iv.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llTitle = findViewById(R.id.llTitle);
                llTitle.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                height = iv.getHeight();
                scrollView = findViewById(R.id.scrollview);
                scrollView.setScrollViewListener(ShopDetailsActivity.this);

            }
        });
    }


    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {

        if (y <= 0) {   //设置标题的背景颜色
            llTitle.setBackgroundColor(Color.argb((int) 0, 255,255,255));
        } else if (y > 0 && y <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变

            float scale = (float) y / height;
            float alpha = (255 * scale);

            tvGoodTitle = findViewById(R.id.tv_good_detail_title_good);

            tvGoodTitle.setTextColor(Color.argb((int) alpha, 1,24,28));
            llTitle.setBackgroundColor(Color.argb((int) alpha, 255,255,255));
        } else {    //滑动到banner下面设置普通颜色
            llTitle.setBackgroundColor(Color.argb((int) 255, 255,255,255));
        }


    }
}