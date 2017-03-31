package com.gsww.www.accountbook.view;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Author : luweicheng on 2017/3/28 0028 08:43
 * E-mail ：1769005961@qq.com
 * GitHub : https://github.com/luweicheng24
 */

public class SplashView extends ViewGroup {
    private int childCount;
    private int mLast;
    private int mFirst;
    private List<Integer> imgaes;
    private int width;//屏幕宽度
    private int height;//屏幕高度
    private int index = 0;
    private Timer timer;
    private Boolean isClick = false;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                if (++index <= childCount - 1) {
                    scrollTo(index * width, 0);
                    if(dotIndexListener!=null){
                        dotIndexListener.indexListener(index);
                    }
                }
            }

        }
    };

    public SplashView(Context context) {
        this(context, null);
    }

    public SplashView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SplashView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        }, 2000, 2000);
    }

    /**
     * 布局
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int leftMargin = 0;
            for (int i = 0; i < childCount; i++) {
                View view = getChildAt(i);
                view.layout(leftMargin, 0, leftMargin + width, height);
                leftMargin += width;
            }
        }
    }

    /**
     * 测量
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        childCount = getChildCount();
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
        height = dm.heightPixels;//轮播图为手机全屏高度的三分之一
        if (childCount == 0) {
            setMeasuredDimension(0, 0);
        } else {
            setMeasuredDimension(width * childCount, height);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                isClick = true;
                mFirst = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                isClick = false;
                mLast = (int) event.getX();
                int distance = mFirst - mLast;
                index = (getScrollX() + (width / 2)) / width;
                if (index == 0 && distance < 0) {
                    return true;
                } else if (index == childCount - 1 && distance > 0) {
                    return true;
                }
                scrollBy(distance, 0);
                mFirst = mLast;
                break;
            case MotionEvent.ACTION_UP:
                index = (getScrollX() + (width / 2)) / width;

                if (index < 0) {
                    index = 0;
                } else if (index == childCount - 1) {
                    index = childCount - 1;
                    /**
                     * 点击了最后一张图片
                     */
                    if(onStartMainListener!= null){
                        onStartMainListener.onClick();
                    }
                }

                scrollTo(index * width, 0);
                if(dotIndexListener!=null){
                    dotIndexListener.indexListener(index);
                }

                break;
        }
        return true;
    }

    public interface DotIndexListener{
        void indexListener(int pos);
    }

    public void setDotIndexListener(DotIndexListener dotIndexListener) {
        this.dotIndexListener = dotIndexListener;
    }

    private DotIndexListener dotIndexListener;

    /**
     * 对最后一张图片进行监听
     */
    public interface OnStartMainListener{
        void onClick();
    }

    public void setOnStartMainListener(OnStartMainListener onStartMainListener) {
        this.onStartMainListener = onStartMainListener;
    }

    private OnStartMainListener onStartMainListener;
}
