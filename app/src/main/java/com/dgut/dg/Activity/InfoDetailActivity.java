package com.dgut.dg.Activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.dgut.dg.R;


public class InfoDetailActivity extends Activity {
    LinearLayout myscrollLinearlayout;
    LinearLayout mainheadview; //顶部个人资料视图
    RelativeLayout mainactionbar; //顶部菜单栏


    private ImageButton userInfoReturnBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_detail);
        initView();
    }
    int Y;
    int position = 0; //拖动Linearlayout的距离Y轴的距离
    int scrollviewdistancetotop = 0; //headView的高
    int menubarHeight = 0;
    int chufaHeight = 0; //需要触发动画的高
    float scale; //像素密度
    int headViewPosition = 0;
    ImageView userinfo_topbar;
    static boolean flag = true;
    static boolean topmenuflag = true;
    private void initView() {

        userInfoReturnBtn = findViewById(R.id.userInfo_returnBtn);

        userinfo_topbar = (ImageView) findViewById(R.id.userinfo_topbar);
//获得像素密度
        scale = this.getResources().getDisplayMetrics().density;
        mainheadview = (LinearLayout) findViewById(R.id.mainheadview);
        mainactionbar = (RelativeLayout) findViewById(R.id.mainactionbar);
        menubarHeight = (int) (55 * scale);
        chufaHeight = (int) (110 * scale);
        scrollviewdistancetotop = (int) ((260 )*scale);
        position = scrollviewdistancetotop;
        myscrollLinearlayout = (LinearLayout) findViewById(R.id.myscrollLinearlayout);
        myscrollLinearlayout.setY( scrollviewdistancetotop); //要减去Absolote布局距离顶部的高度

        userInfoReturnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        myscrollLinearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        myscrollLinearlayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
//按下的Y的位置
                        Y = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int nowY = (int) myscrollLinearlayout.getY(); //拖动界面的Y轴位置
                        int tempY = (int) (event.getRawY() - Y); //手移动的偏移量
                        Y = (int) event.getRawY();
                        if ((nowY + tempY >= 0) && (nowY + tempY <= scrollviewdistancetotop)) {
                            if ((nowY + tempY <= menubarHeight)&& (topmenuflag == true) ){
                                userinfo_topbar.setVisibility(View.VISIBLE);
                                topmenuflag = false;
                            } else if ((nowY + tempY > menubarHeight) && (topmenuflag == flag)) {
                                userinfo_topbar.setVisibility(View.INVISIBLE);
                                topmenuflag = true;
                            }
                            int temp = position += tempY;
                            myscrollLinearlayout.setY(temp);
                            int headviewtemp = headViewPosition += (tempY/5);
                            mainheadview.setY(headviewtemp);
                        }
//顶部的动画效果
                        if ((myscrollLinearlayout.getY() <= chufaHeight) && (flag == true)) {
                            ObjectAnimator anim = ObjectAnimator.ofFloat(mainheadview, "alpha", 1, 0.0f);
                            anim.setDuration(500);
                            anim.start();
                            flag = false;
                        } else if ((myscrollLinearlayout.getY() > chufaHeight + 40) && (flag == false)) {
                            ObjectAnimator anim = ObjectAnimator.ofFloat(mainheadview, "alpha", 0.0f, 1f);
                            anim.setDuration(500);
                            anim.start();
                            flag = true;
                        }
                        break;
                }
                return false;
            }
        });
    }
}