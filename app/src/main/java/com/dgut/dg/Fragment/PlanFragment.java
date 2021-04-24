package com.dgut.dg.Fragment;

import android.Manifest;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dgut.dg.Application.MyApplication;
import com.dgut.dg.Dao.PersonalInfoDao;
import com.dgut.dg.R;
import com.dgut.dg.Utils.Constant;
import com.dgut.dg.Utils.MyProgressView;
import com.dgut.dg.Utils.StepCountCheckUtil;
import com.dgut.dg.Dao.StepDataDao;
import com.dgut.dg.Utils.TimeUtil;
import com.dgut.dg.calendar.BeforeOrAfterCalendarView;
import com.dgut.dg.entity.PersonalInfo;
import com.dgut.dg.entity.StepEntity;
import com.dgut.dg.service.StepService;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.animation.ChartAnimationListener;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.Chart;
import lecho.lib.hellocharts.view.LineChartView;



public class PlanFragment extends Fragment implements Handler.Callback{


    private LinearLayout movementCalenderLl;
    private TextView kmTimeTv;
    private TextView totalKmTv;
//    private TextView stepsTimeTv;
//    private TextView totalStepsTv;
    private TextView supportTv;

    private MyProgressView mTasksView;
    private int DEFAULT_TOTAL_STEP = 10000;
    private static int preStep;

    private double BMI = 0;
    private double CAL = 0;
    private TextView mTvBMI;
    private TextView mTvCAL;

    /**
     * 屏幕长度和宽度
     */
    public static int screenWidth, screenHeight;

    private BeforeOrAfterCalendarView calenderView;

    private String curSelDate;
    private DecimalFormat df = new DecimalFormat("#.##");
    private List<StepEntity> stepEntityList = new ArrayList<>();
    private StepDataDao stepDataDao;

    private Context mContext;
    private PersonalInfoDao personalInfoDao;
    private PersonalInfo personalInfo;


    // 折线表
    private LineChartView chart;
    private final int maxNumberOfLines = 4;
    private final int numberOfPoints = 7;
    private final int number=60;
    float[][] randomNumbersTab = new float[maxNumberOfLines][numberOfPoints];
    private ValueShape shape = ValueShape.CIRCLE;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        preStep = -1;

        mContext = getContext();
        personalInfoDao = new PersonalInfoDao(mContext);
        personalInfo = personalInfoDao.getPersonalInfo(MyApplication.getCurrEmail());

        if (ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{ Manifest.permission.ACTIVITY_RECOGNITION }, 12345);
            }else {
                Log.e("TAG", "onCreate: 版本不支持");
            }
        }

        View view = inflater.inflate(R.layout.fragment_plan, container, false);

        initView(view);
        initData();
        initListener();

        // 折线表
        generateValues();
        generateData();
        resetViewport();

        return view;
    }

    //设置y轴的值从left到number
    private void resetViewport() {
        final Viewport v = new Viewport(chart.getMaximumViewport());
        v.bottom = 0;
        v.top = number;
        v.left = 0;
        v.right = numberOfPoints - 1;
        chart.setMaximumViewport(v);
        chart.setCurrentViewport(v);
    }

    private void generateValues() {
        for (int i = 0; i < maxNumberOfLines; ++i) {
            for (int j = 0; j < numberOfPoints; ++j) {
                randomNumbersTab[i][j] = (float) Math.random() * number;
            }
        }
    }

    private void generateData() {
        List<Line> lines = new ArrayList<Line>();
        List<AxisValue> axisXValues = new ArrayList<AxisValue>();
        for (int i = 0; i <= numberOfPoints; i++)
            axisXValues.add(i, new AxisValue(i).setLabel("星期"+i));
        int numberOfLines = 1;
        for (int i = 0; i < numberOfLines; ++i) {
            List<PointValue> values = new ArrayList<PointValue>();
            for (int j = 0; j < numberOfPoints; j++) {
                values.add(new PointValue(j, randomNumbersTab[i][j]));
            }

            Line line = new Line(values);
            line.setColor(ChartUtils.pickColor());    //设置颜色随机
            line.setShape(shape);           //设置形状
            line.setCubic(true);            //设置线为曲线，反之为折线
            line.setFilled(true);           //设置填满
            line.setHasLabels(true);        //显示便签
            line.setHasLines(true);
            line.setHasPoints(true);
            lines.add(line);
        }

        LineChartData data = new LineChartData(lines);

        data.setAxisXBottom(new Axis(axisXValues).setHasLines(true).setTextColor(Color.BLACK).setName("时间").setHasTiltedLabels(true).setMaxLabelChars(4));
        data.setAxisYLeft(new Axis().setHasLines(true).setName("卡路里").setTextColor(Color.BLACK).setMaxLabelChars(2));
        data.setBaseValue(Float.NEGATIVE_INFINITY);
        chart.setLineChartData(data);

    }



    private void initView(View view) {
        movementCalenderLl = view.findViewById(R.id.movement_records_calender_ll);
        kmTimeTv = view.findViewById(R.id.movement_total_km_time_tv);
        totalKmTv = view.findViewById(R.id.movement_total_km_tv);
//        stepsTimeTv = view.findViewById(R.id.movement_total_steps_time_tv);
//        totalStepsTv = view.findViewById(R.id.movement_total_steps_tv);
        supportTv = view.findViewById(R.id.is_support_tv);



        // 触发
        mTasksView = view.findViewById(R.id.flow_prgress_view);
        mTasksView.setUsedFlow("目标: "+DEFAULT_TOTAL_STEP + "步数");

        // BMI
        mTvBMI = view.findViewById(R.id.tv_BMI_RES);
        mTvCAL = view.findViewById(R.id.tv_CAL_RES);

        // 折线表
        chart=view.findViewById(R.id.chart);


        curSelDate = TimeUtil.getCurrentDate();
    }

    // 这里开始
    public void beginAnim(int total, int curr){

        Log.i("TAG", "beginAnim: ----------------你进来了: " + curr + "-----"+ preStep);

        if (preStep != curr && curr !=  total){
            mTasksView.setmShowProgress(total, curr);
            preStep = curr;
        }else if (total == curr){
            Toast.makeText(getContext(), "恭喜，你完成目标了!", Toast.LENGTH_SHORT).show();
        }else {
            Log.i("TAG", "beginAnim: current step: " + curr);
        }


    }



    private void initData() {

        WindowManager windowManager = getActivity().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        screenWidth = display.getWidth();
        screenHeight = display.getHeight();

        //放到获取宽度之后
        calenderView = new BeforeOrAfterCalendarView(mContext);
        movementCalenderLl.addView(calenderView);

        // 计算BMI
        if (personalInfo != null){
            double height = personalInfo.getHeight() * 0.01;

            int weight = personalInfo.getWeight();
            BMI = weight / (height * height);
            BMI = Math.round(BMI*100) * 0.01;
            mTvBMI.setText(BMI+"");

        }




        /**
         * 这里判断当前设备是否支持计步
         */
        if (StepCountCheckUtil.isSupportStepCountSensor(mContext)) {
            getRecordList();
            supportTv.setVisibility(View.GONE);
            setDatas();
            setupService();
        } else {

            // ***

            mTvCAL.setText(0+"KCal");
            beginAnim(DEFAULT_TOTAL_STEP, 0);
//            totalStepsTv.setText("0");
            supportTv.setVisibility(View.VISIBLE);
        }
    }


    private void initListener() {
        calenderView.setOnBoaCalenderClickListener(new BeforeOrAfterCalendarView.BoaCalenderClickListener() {
            @Override
            public void onClickToRefresh(int position, String curDate) {
                //获取当前选中的时间
                curSelDate = curDate;
                //根据日期去取数据
                setDatas();
            }
        });
    }


    private boolean isBind = false;
    private Messenger mGetReplyMessenger = new Messenger(new Handler(this));
    private Messenger messenger;

    /**
     * 开启计步服务
     */
    private void setupService() {

        Intent intent = new Intent(mContext, StepService.class);

        mContext.startService(intent);
        isBind = mContext.bindService(intent, conn, Service.BIND_AUTO_CREATE);

    }

    /**
     * 定时任务
     */
    private TimerTask timerTask;
    private Timer timer;
    /**
     * 用于查询应用服务（application Service）的状态的一种interface，
     * 更详细的信息可以参考Service 和 context.bindService()中的描述，
     * 和许多来自系统的回调方式一样，ServiceConnection的方法都是进程的主线程中调用的。
     */
    private ServiceConnection conn = new ServiceConnection() {
        /**
         * 在建立起于Service的连接时会调用该方法，目前Android是通过IBind机制实现与服务的连接。
         * @param name 实际所连接到的Service组件名称
         * @param service 服务的通信信道的IBind，可以通过Service访问对应服务
         */
        @Override
        public void onServiceConnected(ComponentName name, final IBinder service) {
            /**
             * 设置定时器，每个三秒钟去更新一次运动步数
             */
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    try {
                        messenger = new Messenger(service);
                        Message msg = Message.obtain(null, Constant.MSG_FROM_CLIENT);
                        msg.replyTo = mGetReplyMessenger;
                        messenger.send(msg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            };
            timer = new Timer();
            timer.schedule(timerTask, 0, 3000);
        }

        /**
         * 当与Service之间的连接丢失的时候会调用该方法，
         * 这种情况经常发生在Service所在的进程崩溃或者被Kill的时候调用，
         * 此方法不会移除与Service的连接，当服务重新启动的时候仍然会调用 onServiceConnected()。
         * @param name 丢失连接的组件名称
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    /**
     * 设置记录数据
     *
     */
    private void setDatas() {


        StepEntity stepEntity;

        if (stepDataDao != null && (stepEntity = stepDataDao.getCurDataByDate(curSelDate) )!= null) {
            int steps = Integer.parseInt(stepEntity.getSteps());

            // ***

            //获取全局的步数
//            totalStepsTv.setText(String.valueOf(steps));

            CAL = Math.round(personalInfo.getWeight()*1.0/2000 * steps * 100) * 0.01;
            mTvCAL.setText(CAL+"KCal");
            beginAnim(DEFAULT_TOTAL_STEP, steps);

            //计算总公里数
            totalKmTv.setText(countTotalKM(steps));
        } else {

            // ***

            mTvCAL.setText(0+"KCal");
            beginAnim(DEFAULT_TOTAL_STEP, 0);
            //获取全局的步数
//            totalStepsTv.setText("0");

            mTvCAL.setText(0+"KCal");
            //计算总公里数
            totalKmTv.setText("0");
        }

        //设置时间
        String time = TimeUtil.getWeekStr(curSelDate);
        kmTimeTv.setText(time);
//        stepsTimeTv.setText(time);
    }

    /**
     * 简易计算公里数，假设一步大约有0.7米
     *
     * @param steps 用户当前步数
     * @return
     */
    private String countTotalKM(int steps) {
        double totalMeters = steps * 0.7;
        //保留两位有效数字
        return df.format(totalMeters / 1000);
    }


    /**
     * 获取全部运动历史纪录
     */
    private void getRecordList() {
        //获取数据库
        stepDataDao = new StepDataDao(mContext);
        stepEntityList.clear();
        stepEntityList.addAll(stepDataDao.getAllDatas());
        if (stepEntityList.size() >= 7) {
            // TODO: 2017/3/27 在这里获取历史记录条数，当条数达到7条或以上时，就开始删除第七天之前的数据,暂未实现

        }

    }




    // 接受service 传递过来的数据更新步数

    @Override
    public boolean handleMessage(@NonNull Message msg) {
        switch (msg.what) {
            //这里用来获取到Service发来的数据
            case Constant.MSG_FROM_SERVER:

                //如果是今天则更新数据
                if (curSelDate.equals(TimeUtil.getCurrentDate())) {
                    //记录运动步数

                    int steps = msg.getData().getInt("steps");
                    //设置的步数

                    // ***

                    CAL = Math.round(personalInfo.getWeight()*1.0/2000 * steps * 100) * 0.01;
                    mTvCAL.setText(CAL+"KCal");


//                    totalStepsTv.setText(String.valueOf(steps));
                    beginAnim(DEFAULT_TOTAL_STEP, steps);

                    Log.i("TAG", "handleMessage: handler每次调用 " + steps);

                    //计算总公里数
                    totalKmTv.setText(countTotalKM(steps));
                }
                break;
        }
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isBind) mContext.unbindService(conn);
    }
}