package com.dgut.dg.Activity;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ScrollView;

import static android.content.ContentValues.TAG;

public class GradationScrollView extends ScrollView {

    // static很重要
    private static ScrollViewListener scrollViewListener = null;

    public interface ScrollViewListener {

        void onScrollChanged(GradationScrollView scrollView, int x, int y,
                             int oldx, int oldy);

    }



    public GradationScrollView(Context context) {
        super(context);
    }

    public GradationScrollView(Context context, AttributeSet attrs,
                               int defStyle) {
        super(context, attrs, defStyle);
    }

    public GradationScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        Log.i(TAG, "setScrollViewListener: ---** 1" + scrollViewListener);
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }else {
            Log.i(TAG, "onScrollChanged: scrollViewListener is null ---** 0");
        }
    }

}