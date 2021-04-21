package com.dgut.dg.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dgut.dg.Adapter.MainFragmentAdapter;
import com.dgut.dg.Application.MyApplication;
import com.dgut.dg.Dao.PersonalInfoDao;
import com.dgut.dg.R;
import com.dgut.dg.Utils.DatabaseHelper;
import com.dgut.dg.entity.PersonalInfo;
import com.dgut.dg.Utils.RandomData;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    // 菜单标题
    private final int[] TAB_TITLES = new int[]{R.string.menu_msg, R.string.menu_contact, R.string.menu_plan, R.string.menu_find, R.string.menu_me};

    // 菜单图标
    private final int[] TAB_IMGS = new int[]{R.drawable.tab_main_msg_selector, R.drawable.tab_main_contact_selector, R.drawable.tab_main_plan_selector, R.drawable.tab_main_find_selector
            , R.drawable.tab_main_me_selector};

    PersonalInfoDao personalInfoDao;

    String TAG = "HomeActivity";

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    private PagerAdapter adapter;

    private long exitTime;

    private SQLiteDatabase db;
    private String email;
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle == null){

            //不通过邮箱直接进来的
            email = MyApplication.getCurrEmail();
        }else {
            email = bundle.getString("email");
            MyApplication.setCurrEmail(email);
        }




        getSupportActionBar().hide();
        ButterKnife.bind(this);

        // 数据库操作
        DatabaseHelper dbHelper = new DatabaseHelper(HomeActivity.this);
        DatabaseHelper dbHelper2 = new DatabaseHelper(HomeActivity.this, "test_db");



        db = dbHelper.getWritableDatabase();

        personalInfoDao = new PersonalInfoDao(getApplicationContext());
//        personalInfo = personalInfoDao.getPersonalInfo();

        // 插入个人信息
        insertData();

        // 插入货物信息
        insertGoodsInfo();

        initPager();
        setTabs(tabLayout, getLayoutInflater(), TAB_TITLES, TAB_IMGS);




    }

    // 插入货物信息
    public void insertGoodsInfo(){

        String query = "select * from goods";
        Cursor cursor = db.rawQuery(query, null);

        String result = "";
        boolean flag = false;

        while (cursor.moveToNext()){
            result =  cursor.getString(cursor.getColumnIndex("id"));
            //已经有数据
            if (result.equals("0")){
                flag = true;
                break;
            }
        }

        if (!flag){

            int[] img = new int[]{R.mipmap.image01,R.mipmap.image02,R.mipmap.image03,R.mipmap.image04,
                    R.mipmap.image05,R.mipmap.image06,R.mipmap.image07,R.mipmap.image08};

            //数据的数量
            int N=img.length;

            for(int i =0; i<N; i++){
                ContentValues contentValues = new ContentValues();

                contentValues.put("id", i);
                contentValues.put("name", "GoodsName");
                contentValues.put("isSelected", 0);
                contentValues.put("imageUrl", "https://python.com");
                contentValues.put("descGoods", "beautiful");
                contentValues.put("price", 5 + new Random().nextInt(10));
                contentValues.put("prime_price", 1555.0 + new Random().nextInt(3000));
                contentValues.put("position", new Random().nextInt(20));
                contentValues.put("count", 1);
                contentValues.put("color", "red");
                contentValues.put("size", "10");
                contentValues.put("goodsImg", img[i%img.length]);
                contentValues.put("isSub", 0);

                long rowId = db.insert("goods", null, contentValues);
                if (rowId != -1){
                    Log.i(TAG, "insertGoodsInfo: 成功插入"+ (i+1) +"条数据！");
                }
            }


        }
    }

    public void insertData(){




        String query = new StringBuilder().append("select * from user where email = '")
                .append(email).append("'").toString();
        Cursor cursor = db.rawQuery(query, null);

        String result = "";
        boolean flag = false;

        while (cursor.moveToNext()){
            Log.i(TAG, "insertData: 进入while");
            result =  cursor.getString(cursor.getColumnIndex("email"));

            if (result.equals(email)){
                flag = true;
                break;
            }
        }


        // 新用户，生成默认数据

        if (!flag){
            ContentValues contentValues = new ContentValues();
            Log.i(TAG, "insertData: 新用户 " + email);
            contentValues.put("email", email);
            contentValues.put("name", "小白");
            contentValues.put("gender", "男");
            contentValues.put("birthday", "18");
            contentValues.put("height", 175);
            contentValues.put("weight", 65);
            contentValues.put("address", "广东省东莞市");


            personalInfoDao.insertPersonalInfo(contentValues);
            Log.i(TAG, "insertData: 新用户，成功插入数据");

        }



    }


    private void setTabs(TabLayout tabLayout, LayoutInflater inflater, int[] tabTitlees, int[] tabImgs){
        for (int i=0; i<tabImgs.length; i++){
            TabLayout.Tab tab = tabLayout.newTab();
            View view = inflater.inflate(R.layout.item_main_menu, null);
            tab.setCustomView(view);

            TextView tvTitle = view.findViewById(R.id.txt_tab);
            tvTitle.setText(tabTitlees[i]);

            ImageView imgTab = view.findViewById(R.id.img_tab);
            imgTab.setImageResource(tabImgs[i]);

            tabLayout.addTab(tab);
        }

    }


    private void initPager(){

        // ViewPager是一个容器，该组件所显示的内容由它的Adapter提供，因此使用ViewPager必须为它设置一个Adapter
        // ViewPager需要设置一个Adapter，该Adapter需要返回不同的fragment（比如页面切换）


        adapter = new MainFragmentAdapter(getSupportFragmentManager(), TAB_TITLES.length);
        viewPager.setAdapter(adapter);

        // 关联切换
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN
                && event.getRepeatCount() == 0) {
            // 重写键盘事件分发，onKeyDown方法某些情况下捕获不到，只能在这里写
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Snackbar snackbar = Snackbar.make(viewPager, "再按一次退出程序", Snackbar.LENGTH_SHORT);
                snackbar.getView().setBackgroundResource(R.color.colorPrimary);
                snackbar.show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }



}












