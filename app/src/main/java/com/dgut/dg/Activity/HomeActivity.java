package com.dgut.dg.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


import android.content.ContentValues;
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
    private final int[] TAB_TITLES = new int[]{R.string.menu_msg, R.string.menu_contact, R.string.menu_find, R.string.menu_me};

    // 菜单图标
    private final int[] TAB_IMGS = new int[]{R.drawable.tab_main_msg_selector, R.drawable.tab_main_contact_selector, R.drawable.tab_main_find_selector
            , R.drawable.tab_main_me_selector};


    String TAG = "HomeActivity";

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    private PagerAdapter adapter;

    private long exitTime;

    private SQLiteDatabase db;
    

    // 当第一次创建数据库时回调该方法
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        

        getSupportActionBar().hide();

        ButterKnife.bind(this);

        // 数据库操作
        DatabaseHelper dbHelper = new DatabaseHelper(HomeActivity.this, "userdb", null, 1);
        db = dbHelper.getWritableDatabase();

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
            if (result.equals("0")){
                Log.i(TAG, "insertGoodsInfo: %% "+ cursor.getString(cursor.getColumnIndex("imageUrl")));
                flag = true;
                break;
            }
        }

        if (!flag){

            int[] img = {R.drawable.cmaz, R.drawable.cmaz, R.drawable.cmaz, R.drawable.cmaz, R.drawable.cmaz, R.drawable.cmaz};

            //数据的数量
            int N=5;

            for(int i =0; i<N; i++){
                ContentValues contentValues = new ContentValues();

                contentValues.put("id", i);
                contentValues.put("name", "GoodsName");
                contentValues.put("isSelected", 0);
                contentValues.put("imageUrl", "https://python.com");
                contentValues.put("descGoods", "beautiful");
                contentValues.put("price", 255.0 + new Random().nextInt(3000));
                contentValues.put("prime_price", 1555.0 + new Random().nextInt(3000));
                contentValues.put("position", new Random().nextInt(20));
                contentValues.put("count", new Random().nextInt(50));
                contentValues.put("color", "red");
                contentValues.put("size", "10");
                contentValues.put("goodsImg", img[i%img.length]);

                long rowId = db.insert("goods", null, contentValues);
                if (rowId != -1){
                    Log.i(TAG, "insertGoodsInfo: 成功插入"+ (i+1) +"条数据！");
                }
            }


        }
    }

    public void insertData(){

        // 登陆的邮箱
        String email = PersonalInfo.getEmail();

        String query = new StringBuilder().append("select * from user where email = '")
                .append(email).append("'").toString();
        Cursor cursor = db.rawQuery(query, null);

        String result = "";
        boolean flag = false;

        while (cursor.moveToNext()){
            result =  cursor.getString(cursor.getColumnIndex("email"));
            if (result.equals(email)){
                // 旧用户
                PersonalInfo.setName(cursor.getString(cursor.getColumnIndex("name")));
                PersonalInfo.setGender(cursor.getString(cursor.getColumnIndex("gender")));
                PersonalInfo.setAge(cursor.getInt(cursor.getColumnIndex("age")));
                PersonalInfo.setHeight(cursor.getDouble(cursor.getColumnIndex("height")));
                PersonalInfo.setWeight(cursor.getDouble(cursor.getColumnIndex("weight")));

                flag = true;
                break;
            }
        }

        // 新用户
        if (!flag){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ContentValues contentValues = new ContentValues();

                    // 默认邮箱
                    if (email == "" || email == null){
                        PersonalInfo.setEmail("893461@qq.com");
                    }

                    // 用户名
                    PersonalInfo.setName(new RandomData().getRandomString());

                    // 性别
                    PersonalInfo.setGender("male");

                    // 年龄
                    PersonalInfo.setAge(18);

                    // 身高
                    PersonalInfo.setHeight(175.0);
                    // 体重
                    PersonalInfo.setWeight(65);

                    contentValues.put("email", email);
                    contentValues.put("name", PersonalInfo.getName());
                    contentValues.put("gender", PersonalInfo.getGender());
                    contentValues.put("age", PersonalInfo.getAge());
                    contentValues.put("height", PersonalInfo.getHeight());
                    contentValues.put("weight", PersonalInfo.getWeight());

                    db.insert("user", null, contentValues);

                }
            }).run();
        }




    }


    private void setTabs(TabLayout tabLayout, LayoutInflater inflater, int[] tabTitlees, int[] tabImgs){
        for (int i=0; i<tabImgs.length; i++){
            TabLayout.Tab tab = tabLayout.newTab();
            View view = inflater.inflate(R.layout.item_main_menu, null);
            tab.setCustomView(view);

            TextView tvTitle = (TextView) view.findViewById(R.id.txt_tab);
            tvTitle.setText(tabTitlees[i]);

            ImageView imgTab = (ImageView) view.findViewById(R.id.img_tab);
            imgTab.setImageResource(tabImgs[i]);

            tabLayout.addTab(tab);
        }

    }


    private void initPager(){

        // ViewPager是一个容器，该组件所显示的内容由它的Adapter提供，因此使用ViewPager必须为它设置一个Adapter
        // ViewPager需要设置一个Adapter，该Adapter需要返回不同的fragment（比如页面切换）


        adapter = new MainFragmentAdapter(getSupportFragmentManager());
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












