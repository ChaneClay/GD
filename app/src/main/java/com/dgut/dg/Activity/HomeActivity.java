package com.dgut.dg.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Person;
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
import com.dgut.dg.Utils.PersonalMes;
import com.dgut.dg.Utils.RandomData;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

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


        ButterKnife.bind(this);

        // 数据库操作
        DatabaseHelper dbHelper = new DatabaseHelper(HomeActivity.this, "user_db", null, 1);
        db = dbHelper.getWritableDatabase();

        insertData();

        initPager();
        setTabs(tabLayout, getLayoutInflater(), TAB_TITLES, TAB_IMGS);

    }


    public void insertData(){

        // 登陆的邮箱
        String email = PersonalMes.getEmail();

        String query = new StringBuilder().append("select * from user where email = '")
                .append(email).append("'").toString();
        Cursor cursor = db.rawQuery(query, null);

        String result = "";
        boolean flag = false;

        while (cursor.moveToNext()){
            result =  cursor.getString(cursor.getColumnIndex("email"));
            if (result.equals(email)){
                // 旧用户
                PersonalMes.setName(cursor.getString(cursor.getColumnIndex("name")));
                PersonalMes.setGender(cursor.getString(cursor.getColumnIndex("gender")));
                PersonalMes.setAge(cursor.getInt(cursor.getColumnIndex("age")));
                PersonalMes.setHeight(cursor.getDouble(cursor.getColumnIndex("height")));
                PersonalMes.setWeight(cursor.getDouble(cursor.getColumnIndex("weight")));

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
                        PersonalMes.setEmail("893461@qq.com");
                    }

                    // 用户名
                    PersonalMes.setName(new RandomData().getRandomString());

                    // 性别
                    PersonalMes.setGender("male");

                    // 年龄
                    PersonalMes.setAge(18);

                    // 身高
                    PersonalMes.setHeight(175.0);
                    // 体重
                    PersonalMes.setWeight(65);


                    contentValues.put("email", email);
                    contentValues.put("name", PersonalMes.getName());
                    contentValues.put("gender", PersonalMes.getGender());
                    contentValues.put("age", PersonalMes.getAge());
                    contentValues.put("height", PersonalMes.getHeight());
                    contentValues.put("weight", PersonalMes.getWeight());

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












