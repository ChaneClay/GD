package com.dgut.dg.Activity;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.dgut.dg.Application.MyApplication;
import com.dgut.dg.Dao.PersonalInfoDao;
import com.dgut.dg.R;
import com.dgut.dg.Utils.CardBean;
import com.dgut.dg.Utils.GetJsonDataUtil;
import com.dgut.dg.Utils.JsonBean;
import com.dgut.dg.Utils.RoundImageView;
import com.dgut.dg.entity.PersonalInfo;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class InfoDetailActivity extends Activity implements View.OnClickListener{
    LinearLayout myScrollLinearLayout;
    LinearLayout mainHeadView;          //顶部个人资料视图
    RelativeLayout mainActionBar;       //顶部菜单栏


    private ImageButton userInfoReturnBtn;

    private PersonalInfoDao personalInfoDao;
    private PersonalInfo personalInfo;


    int Y;
    int position = 0;                   //拖动LinearLayout的距离Y轴的距离
    int scrollViewDistanceToTop = 0;    //headView的高
    int menuBarHeight = 0;
    int chufaHeight = 0;        //需要触发动画的高
    float scale;                //像素密度
    int headViewPosition = 0;
    ImageView userInfo_topBar;
    static boolean flag = true;
    static boolean topMenuFlag = true;


    // 详细信息


    private TextView mTvGender;     // 性别
    private TextView mAddress;      // 地址
    private TextView mBirth;        // 生日
    private TextView mHeight;
    private TextView mWeight;


    private TextView mName;
    private Button mBtnSave;
    private Button mBtnCancel;



    private OptionsPickerView pvCustomOptions, pvNoLinkOptions;
    private TimePickerView pvCustomLunar;
    private ArrayList<CardBean> cardItem = new ArrayList<>();
    private ArrayList<String> food = new ArrayList<>();
    private ArrayList<String> clothes = new ArrayList<>();
    private ArrayList<String> computer = new ArrayList<>();

    // 地址解析

    private List<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private Thread thread;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_detail);

        personalInfoDao = new PersonalInfoDao(getApplicationContext());
        personalInfo = personalInfoDao.getPersonalInfo(MyApplication.getCurrEmail());

        initView();
        initData();



    }

    private void initData() {

        mName.setText(personalInfo.getName());
        mBirth.setText(personalInfo.getBirthday());
        mAddress.setText(personalInfo.getAddress());
        mTvGender.setText(personalInfo.getGender());
        mHeight.setText(personalInfo.getHeight()+"cm");
        mWeight.setText(personalInfo.getWeight()+"kg");
    }


    @SuppressLint("ClickableViewAccessibility")
    private void initView() {

        userInfoReturnBtn = findViewById(R.id.userInfo_returnBtn);

        userInfo_topBar = (ImageView) findViewById(R.id.userInfo_topBar);
        //获得像素密度
        scale = this.getResources().getDisplayMetrics().density;
        mainHeadView = (LinearLayout) findViewById(R.id.mainHeadView);
        mainActionBar = (RelativeLayout) findViewById(R.id.mainActionBar);
        menuBarHeight = (int) (55 * scale);
        chufaHeight = (int) (110 * scale);
        scrollViewDistanceToTop = (int) ((260 )*scale);
        position = scrollViewDistanceToTop;
        myScrollLinearLayout = (LinearLayout) findViewById(R.id.myScrollLinearLayout);
        myScrollLinearLayout.setY( scrollViewDistanceToTop); //要减去Absolute布局距离顶部的高度



        getOptionData();
        initLunarPicker();
        initCustomOptionPicker();

        mTvGender = findViewById(R.id.tv_gender);
        mAddress = findViewById(R.id.tv_address);
        mBirth = findViewById(R.id.tv_birth);
        mName = findViewById(R.id.tv_name);
        mHeight = findViewById(R.id.tv_height);
        mWeight = findViewById(R.id.tv_weight);
        mBtnSave = findViewById(R.id.btn_save);
        mBtnCancel = findViewById(R.id.btn_cancel);



        mTvGender.setOnClickListener(this);
        mAddress.setOnClickListener(this);
        mBirth.setOnClickListener(this);
        mHeight.setOnClickListener(this);
        mWeight.setOnClickListener(this);
        mBtnSave.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);


        userInfoReturnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        myScrollLinearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //按下的Y的位置
                        Y = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int nowY = (int) myScrollLinearLayout.getY(); //拖动界面的Y轴位置
                        int tempY = (int) (event.getRawY() - Y); //手移动的偏移量
                        Y = (int) event.getRawY();
                        if ((nowY + tempY >= 0) && (nowY + tempY <= scrollViewDistanceToTop)) {
                            if ((nowY + tempY <= menuBarHeight)&& (topMenuFlag == true) ){
                                userInfo_topBar.setVisibility(View.VISIBLE);
                                topMenuFlag = false;
                            } else if ((nowY + tempY > menuBarHeight) && (topMenuFlag == flag)) {
                                userInfo_topBar.setVisibility(View.INVISIBLE);
                                topMenuFlag = true;
                            }
                            int temp = position += tempY;
                            myScrollLinearLayout.setY(temp);
                            int headViewTemp = headViewPosition += (tempY/5);
                            mainHeadView.setY(headViewTemp);
                        }
                        //顶部的动画效果
                        if ((myScrollLinearLayout.getY() <= chufaHeight) && (flag == true)) {
                            ObjectAnimator anim = ObjectAnimator.ofFloat(mainHeadView, "alpha", 1, 0.0f);
                            anim.setDuration(500);
                            anim.start();
                            flag = false;
                        } else if ((myScrollLinearLayout.getY() > chufaHeight + 40) && (flag == false)) {
                            ObjectAnimator anim = ObjectAnimator.ofFloat(mainHeadView, "alpha", 0.0f, 1f);
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



    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_gender && pvCustomOptions != null) {

            // 条件选择自定义布局
            pvCustomOptions.show(); //弹出自定义条件选择器

        }else if (v.getId() == R.id.tv_address) {//跳转到 省市区解析示例页面
            // 地址
            loadData();

        }else if (v.getId() == R.id.tv_birth) {

            // 日期
            pvCustomLunar.show();
        }else if(v.getId() == R.id.btn_save){
            // 保存资料
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", mName.getText()+"");
            contentValues.put("birthday", mBirth.getText()+"");
            contentValues.put("gender", mTvGender.getText()+"");
            contentValues.put("address", mAddress.getText()+"");

            String height = mHeight.getText()+"";
            int real_height = Integer.parseInt(height.replace("cm", ""));
            contentValues.put("height", real_height);

            String weight = mWeight.getText() + "";
            int real_weight = Integer.parseInt(weight.replace("kg", ""));
            contentValues.put("weight", real_weight);

            personalInfo = personalInfoDao.updatePersonalInfo(contentValues, MyApplication.getCurrEmail());
            initData();
            Toast.makeText(getApplicationContext(), "成功保存数据", Toast.LENGTH_SHORT).show();


        }else if(v.getId() == R.id.btn_cancel){
            // 取消
            initData();

        }else if(v.getId() == R.id.tv_height || v.getId() == R.id.tv_weight){

            Intent intent = new Intent(InfoDetailActivity.this, TestCircleWheelViewActivity.class);
            Bundle bundle = new Bundle();
            if (v.getId() == R.id.tv_height){
                bundle.putString("flag", "0");
            }else {
                bundle.putString("flag", "1");
            }

            intent.putExtras(bundle);
            startActivityForResult(intent, 0x123);

        }else {
            Log.e("TAG", "onClick: 没有符合条件的" );
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 0x123 && resultCode == 0x123){
            Bundle bundle = data.getExtras();
            String height = bundle.getString("height");
            mHeight.setText(height);
        }else if(requestCode == 0x123 && resultCode == 0x456){
            Bundle bundle = data.getExtras();
            String weight = bundle.getString("weight");
            mWeight.setText(weight);
        }

    }

    private void loadData(){

        if (thread == null){
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    initJsonData();
                }
            });

        }
        thread.start();
    }


    /**
     * 农历时间已扩展至 ： 1900 - 2100年
     */
    private void initLunarPicker() {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2014, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2069, 2, 28);
        //时间选择器 ，自定义布局
        pvCustomLunar = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调

                mBirth.setText(getTime(date));

//                Toast.makeText(InfoDetailActivity.this, getTime(date), Toast.LENGTH_SHORT).show();
            }
        })
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_lunar, new CustomListener() {

                    @Override
                    public void customLayout(final View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomLunar.returnData();
                                pvCustomLunar.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomLunar.dismiss();
                            }
                        });
                        //公农历切换
                        CheckBox cb_lunar = (CheckBox) v.findViewById(R.id.cb_lunar);
                        cb_lunar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                pvCustomLunar.setLunarCalendar(!pvCustomLunar.isLunarCalendar());
                                //自适应宽
                                setTimePickerChildWeight(v, isChecked ? 0.8f : 1f, isChecked ? 1f : 1.1f);
                            }
                        });

                    }

                    /**
                     * 公农历切换后调整宽
                     * @param v
                     * @param yearWeight
                     * @param weight
                     */
                    private void setTimePickerChildWeight(View v, float yearWeight, float weight) {
                        ViewGroup timePicker = (ViewGroup) v.findViewById(R.id.timepicker);
                        View year = timePicker.getChildAt(0);
                        LinearLayout.LayoutParams lp = ((LinearLayout.LayoutParams) year.getLayoutParams());
                        lp.weight = yearWeight;
                        year.setLayoutParams(lp);
                        for (int i = 1; i < timePicker.getChildCount(); i++) {
                            View childAt = timePicker.getChildAt(i);
                            LinearLayout.LayoutParams childLp = ((LinearLayout.LayoutParams) childAt.getLayoutParams());
                            childLp.weight = weight;
                            childAt.setLayoutParams(childLp);
                        }
                    }
                })
                .setType(new boolean[]{true, true, true, false, false, false})
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(Color.RED)
                .build();
    }

    private void getOptionData() {
        /*
         * 注意：如果是添加JavaBean实体数据，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        getCardData();
        getNoLinkData();

    }

    // 符合条件 性别
    private void getCardData() {

        cardItem.add(new CardBean(0, "男"));
        cardItem.add(new CardBean(1, "女"));
        cardItem.add(new CardBean(2, "保密"));

        for (int i = 0; i < cardItem.size(); i++) {
            if (cardItem.get(i).getCardNo().length() > 6) {
                String str_item = cardItem.get(i).getCardNo().substring(0, 6) + "...";
                cardItem.get(i).setCardNo(str_item);
            }
        }
    }


    // 身高体重
    private void getNoLinkData() {
        food.add("KFC");
        food.add("MacDonald");
        food.add("Pizza hut");

        clothes.add("Nike");
        clothes.add("Adidas");
        clothes.add("Armani");

        computer.add("ASUS");
        computer.add("Lenovo");
        computer.add("Apple");
        computer.add("HP");
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
//        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    // 性别
    private void initCustomOptionPicker() {//条件选择器初始化，自定义布局
        /**
         * @description
         *
         * 注意事项：
         * 自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针。
         * 具体可参考demo 里面的两个自定义layout布局。
         */
        pvCustomOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = cardItem.get(options1).getPickerViewText();
                mTvGender.setText(tx);
            }
        })
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        final TextView tvAdd = (TextView) v.findViewById(R.id.tv_add);
                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);

                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomOptions.returnData();
                                pvCustomOptions.dismiss();
                            }
                        });

                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomOptions.dismiss();
                            }
                        });

                        tvAdd.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getCardData();
                                pvCustomOptions.setPicker(cardItem);
                            }
                        });
                    }
                })
                .isDialog(true)
                .setOutSideCancelable(false)
                .build();

        pvCustomOptions.setPicker(cardItem);//添加数据

    }


    // 不联动的多级选项
//    private void initNoLinkOptionsPicker() {
//        pvNoLinkOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
//
//
//            @Override
//            public void onOptionsSelect(int options1, int options2, int options3, View v) {
//
//                String str = "food:" + food.get(options1)
//                        + "\nclothes:" + clothes.get(options2)
//                        + "\ncomputer:" + computer.get(options3);
//
//                mTvHeiWei.setText(str);
//
////                Toast.makeText(InfoDetailActivity.this, str, Toast.LENGTH_SHORT).show();
//            }
//        })
//                .setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
//                    @Override
//                    public void onOptionsSelectChanged(int options1, int options2, int options3) {
//                        String str = "options1: " + options1 + "\noptions2: " + options2 + "\noptions3: " + options3;
//                        mTvHeiWei.setText(str);
//
////                        Toast.makeText(InfoDetailActivity.this, str, Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .setItemVisibleCount(5)
//                .build();
//        pvNoLinkOptions.setNPicker(food, clothes, computer);
//        pvNoLinkOptions.setSelectOptions(0, 1, 1);
//    }

    //---------------------------------
    // 地址解析

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_SUCCESS:

                    showPickerView();
                    break;

                case MSG_LOAD_FAILED:
                    Toast.makeText(InfoDetailActivity.this, "Parse Failed", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };


    private void showPickerView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String opt1tx = options1Items.size() > 0 ?
                        options1Items.get(options1).getPickerViewText() : "";

                String opt2tx = options2Items.size() > 0
                        && options2Items.get(options1).size() > 0 ?
                        options2Items.get(options1).get(options2) : "";

                String opt3tx = options2Items.size() > 0
                        && options3Items.get(options1).size() > 0
                        && options3Items.get(options1).get(options2).size() > 0 ?
                        options3Items.get(options1).get(options2).get(options3) : "";

                String tx = opt1tx + opt2tx + opt3tx;

                Log.i("TAG", "onOptionsSelect: 城市弹出！" + tx);
                mAddress.setText(tx);

//                Toast.makeText(InfoDetailActivity.this, tx, Toast.LENGTH_SHORT).show();
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */

        // 添加了new
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String cityName = jsonBean.get(i).getCityList().get(c).getName();
                cityList.add(cityName);//添加城市
                ArrayList<String> city_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                /*if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    city_AreaList.add("");
                } else {
                    city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }*/
                city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                province_AreaList.add(city_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(cityList);

            /**
             * 添加地区数据
             */
            options3Items.add(province_AreaList);
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }


    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }


}