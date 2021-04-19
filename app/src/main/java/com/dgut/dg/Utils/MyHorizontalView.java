package com.dgut.dg.Utils;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import com.dgut.dg.Adapter.MyAdapter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by antimage on 2016/1/9.
 */
public class MyHorizontalView extends HorizontalScrollView
        implements View.OnClickListener {

    private String TAG = "MyHorizontalView";

    private ViewGroup parent;
    private int screenWidth;
    /* 当前最后一张图片的index*/
    private int mCurrentIndex;
    /* 当前第一张图片的下标*/
    private int mFristIndex;
    /* 每屏幕最多显示的个数*/
    private int mCountOneScreen;
    /* 子元素的宽度*/
    private int mChildWidth;
    /* 子元素的高度*/
    private int mChildHeight;

    private MyAdapter mAdapter;

    private CurrentImageChangeListener mListener;

    private OnItemClickListener mOnItemClickListener;

    /**
     * 图片滚动时的回调接口
     */
    public interface CurrentImageChangeListener {
        void onCurrentImgChanged(int position, View viewIndicator);
    }

    /**
     * 点击条目时的回调
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int pos);
    }

    /* 保存View与位置的键值对 */
    private Map<View, Integer> mViewPos = new HashMap<>();

    public MyHorizontalView(Context context) {
        this(context, null);
    }

    public MyHorizontalView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyHorizontalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.setSmoothScrollingEnabled(true);

        DisplayMetrics metrics = new DisplayMetrics();
        // 取得窗口属性
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        // 窗口的宽度 （像素）
        screenWidth = metrics.widthPixels;
    }

    /**
     * 初始化数据，设置数据适配器
     */
    public void initDatas(MyAdapter mAdapter) {

        if (getChildCount() == 0) {
            Log.e(TAG, "必须要有子元素");
        }
        if (getChildCount() == 0 || mAdapter == null)
            return;

        this.mAdapter = mAdapter;
        parent = (ViewGroup) getChildAt(0);

        // 获得适配器中第一个View
        final View view = mAdapter.getView(0, null, parent);
        parent.addView(view);

        // 强制计算当前View的宽和高
        if (mChildWidth == 0 && mChildHeight == 0) {
            int w = MeasureSpec.makeMeasureSpec(0,
                    MeasureSpec.UNSPECIFIED);
            int h = MeasureSpec.makeMeasureSpec(0,
                    MeasureSpec.UNSPECIFIED);

            view.measure(w, h);
            mChildHeight = view.getMeasuredHeight();
            mChildWidth = view.getMeasuredWidth();
            Log.e(TAG, "子组件的宽：" + mChildWidth + ", 子组件的高：" + mChildHeight);

            // 计算每次加载多少个View
            mCountOneScreen = screenWidth / mChildWidth + 2;

            Log.e(TAG, "mCountOneScreen = " + mCountOneScreen
                    + " ,mChildWidth = " + mChildWidth);
        }
        //初始化第一屏幕的元素
        loadFirstChild(mCountOneScreen);
    }

    /**
     * 加载第一屏的View
     */
    public void loadFirstChild(int mCountOneScreen) {

        parent.removeAllViews();
        mViewPos.clear();

        for (int i = 0; i < mCountOneScreen; i++) {
            View view = mAdapter.getView(i, null, parent);
            view.setOnClickListener(this);
            parent.addView(view);
            mViewPos.put(view, i);
            mCurrentIndex = i;
        }

        if (mListener != null) {
            notifyCurrentImageChanged();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:

                int scrollX = getScrollX();
                // 如果当前scrollX为view的宽度，加载下一张，移除第一张
                if (scrollX >= mChildWidth) {
                    loadNextImage();
                }
                // 如果当前scrollX = 0， 往前设置一张，移除最后一张
                if (scrollX == 0) {
                    loadPreImage();
                }
                break;
        }
        // 这里无论返回值是设置 true 还是 false
        // HorizontalScrollView都不会滑动
        return super.onTouchEvent(event);
    }

    /**
     * 加载下一张图片
     */
    protected void loadNextImage() {
        // 数组边界值计算
        if (mCurrentIndex == mAdapter.getCount() - 1) {
            return;
        }
        //移除第一张图片，且将水平滚动位置置0（图片有宽度，所以为置0）
        scrollTo(0, 0);
        mViewPos.remove(parent.getChildAt(0));
        parent.removeViewAt(0);

        //获取下一张图片，并且设置onClick事件，且加入容器中
        View view = mAdapter.getView(++mCurrentIndex, null, parent);
        Log.e(TAG, "mCurrentIndex ===" + mCurrentIndex);
        view.setOnClickListener(this);
        parent.addView(view);
        mViewPos.put(view, mCurrentIndex);

        //当前第一张图片小标
        mFristIndex++;
        //如果设置了滚动监听则触发
        if (mListener != null) {
            notifyCurrentImageChanged();
        }

    }

    /**
     * 加载前一张图片
     */
    protected void loadPreImage() {
        //如果当前已经是第一张，则返回
        if (mFristIndex == 0)
            return;
        //获得当前应该显示为第一张图片的下标
        int index = mCurrentIndex - mCountOneScreen;
        if (index >= 0) {
            //移除最后一张
            int oldViewPos = parent.getChildCount() - 1;
            mViewPos.remove(parent.getChildAt(oldViewPos));
            parent.removeViewAt(oldViewPos);

            //将此View放入第一个位置
            View view = mAdapter.getView(index, null, parent);
            mViewPos.put(view, index);
            parent.addView(view, 0);
            view.setOnClickListener(this);
            //水平滚动位置向左移动view的宽度个像素
            scrollTo(mChildWidth, 0);
            //当前位置--，当前第一个显示的下标--
            mCurrentIndex--;
            mFristIndex--;
            //回调
            if (mListener != null) {
                notifyCurrentImageChanged();
            }
        }
    }

    /**
     * 滑动时的回调
     */
    public void notifyCurrentImageChanged() {

        int sum = parent.getChildCount();
        for (int i = 0; i < sum; i++) {
            // 清除所有的背景色，点击时会设置为蓝色
            parent.getChildAt(i).setBackgroundColor(Color.WHITE);
        }

        mListener.onCurrentImgChanged(mFristIndex, parent.getChildAt(0));
    }

    @Override
    public void onClick(View v) {

        if (mOnItemClickListener != null) {

            int sum = parent.getChildCount();
            for (int i = 0; i < sum; i++) {
                parent.getChildAt(i).setBackgroundColor(Color.WHITE);
            }
            mOnItemClickListener.onItemClick(v, mViewPos.get(v));
        }
    }

    public void setOnItemClickListener(OnItemClickListener mOnClickListener) {
        this.mOnItemClickListener = mOnClickListener;
    }

    public void setCurrentImageChangeListener(CurrentImageChangeListener mListener) {
        this.mListener = mListener;
    }
}