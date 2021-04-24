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
import com.dgut.dg.Utils.GetJsonDataUtil;
import com.dgut.dg.entity.NewsEntity;
import com.dgut.dg.entity.PersonalInfo;
import com.dgut.dg.Utils.RandomData;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
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
        initPager();
        setTabs(tabLayout, getLayoutInflater(), TAB_TITLES, TAB_IMGS);


        // 数据库操作
        DatabaseHelper dbHelper = new DatabaseHelper(HomeActivity.this);
        DatabaseHelper dbHelper2 = new DatabaseHelper(HomeActivity.this, "test_db");
        db = dbHelper.getWritableDatabase();

        personalInfoDao = new PersonalInfoDao(getApplicationContext());

        // 插入个人信息
        insertData();
        // 插入货物信息
        insertGoodsInfo();
        // 插入资讯
        insertNewsEntity();


    }

    // 插入资讯
    private void insertNewsEntity() {
        String query = "select * from news";
        Cursor cursor = db.rawQuery(query, null);
        String result = "";
        boolean flag = false;

        while (cursor.moveToNext()){
            result =  cursor.getString(cursor.getColumnIndex("id"));
            if (result.equals("0")){
                flag = true;
                break;
            }
        }

        if (!flag){
            List<NewsEntity> dataList = initJsonData();
            for (int i = 0; i < dataList.size(); i++) {

                ContentValues contentValues = new ContentValues();

                contentValues.put("id", dataList.get(i).getId());
                contentValues.put("date", dataList.get(i).getDate());
                contentValues.put("thumbUrl", dataList.get(i).getThumbUrl());
                contentValues.put("title", dataList.get(i).getTitle());
                contentValues.put("url", dataList.get(i).getUrl());
                contentValues.put("isSel", dataList.get(i).getIsSel());

                long rowId = db.insert("news", null, contentValues);
                if (rowId != 0){
                    Log.i(TAG, "insertNewsEntity: 成功插入"+ (i+1) +"条数据！");
                }


            }

        }



    }

    public ArrayList<NewsEntity> parseData(String result) {//Gson 解析
        ArrayList<NewsEntity> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                NewsEntity entity = gson.fromJson(data.optJSONObject(i).toString(), NewsEntity.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }

    public ArrayList<NewsEntity> initJsonData(){
        String JsonData = new GetJsonDataUtil().getJson(getApplicationContext(), "news.json");//获取assets目录下的json文件数据
        ArrayList<NewsEntity> jsonBean = parseData(JsonData);     //用Gson 转成实体
        return jsonBean;
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
                    R.mipmap.image05,R.mipmap.image06,R.mipmap.image07,R.mipmap.image08,R.mipmap.image09,R.mipmap.image10};





            //数据的数量
            int N=img.length;
            int row = N;
            int col = 5;
            String [][]GoodsMessage = {
                    // 名字   描述  价格  原价格 颜色
                {"燃脂跳绳手胶版", "燃脂跳绳手胶版，吸汗防滑，手感舒适，给你舒适的运动体验！", "29", "39", "红色"},
                {"燃脂跳绳通用版", "轻盈好跳，随身便携，可调长短，软质球体不缠绕，减少噪音", "39", "99", "黑色"},
                {"弹力绳套组", "天然乳胶管材质，拉力均匀，抗老化；随时随地地开展家庭健身", "119", "149", "黑色"},
                {"肌肉放松泡沫轴 经典款/便携款", "滚走肌肉酸痛，滚出纤细身材！软硬适中，拒绝刺痛感", "59", "99", "蓝色"},
                {"防滑导汗发带", "内附硅胶条，防滑不紧绷，无惧汗水打扰", "19", "19", "棕色"},
                {"专业铸铁壶铃", "全身发力，塑形燃脂！4种，重量，任你挑选", "89", "199", "黑色"},
                {"智能手环B2会员版", "彩显大屏，14天续航，5ATM防水等级，手环游戏模式，卡路里管家", "99", "99", "黑色"},
                {"天然橡胶瑜伽垫3mm", "轻薄轻巧， 方便携带，防滑吸汗，高阶习练，户外瑜伽首选！", "179", "199", "绿色"},
                {"智能跑步机K2", "全新能量回弹跑板，智能调节跑速，开合折叠轻松收纳，语音指导，满足不同跑步需求！", "2599", "3099", "黑色"},
                {"智能动感单车C1 Lite", "为家庭减脂而生，精致小巧，高回弹座椅，快捷高度调节，智能调阻，静音不占地", "2099", "2699", "灰色"},

            };



            for(int i =0; i<N; i++){
                ContentValues contentValues = new ContentValues();

                contentValues.put("id", i);

                contentValues.put("name", GoodsMessage[i][0]);

                contentValues.put("isSelected", 0);
                contentValues.put("imageUrl", "https://python.com");

                contentValues.put("descGoods", GoodsMessage[i][1]);

                contentValues.put("price", Integer.parseInt(GoodsMessage[i][2]));
                contentValues.put("prime_price", Integer.parseInt(GoodsMessage[i][3]));

                contentValues.put("position", new Random().nextInt(20));
                contentValues.put("count", new Random().nextInt(20)+10);

                contentValues.put("color", GoodsMessage[i][4]);

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












