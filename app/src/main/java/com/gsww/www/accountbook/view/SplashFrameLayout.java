package com.gsww.www.accountbook.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gsww.com.accountbook.R;
import com.gsww.www.accountbook.utils.L;

import java.util.List;

/**
 * Author : luweicheng on 2017/3/28 0028 09:55
 * E-mail ：1769005961@qq.com
 * GitHub : https://github.com/luweicheng24
 */

public class SplashFrameLayout extends FrameLayout implements SplashView.DotIndexListener {
    private SplashView splashView;
    private LinearLayout dotLayout;
    private List<Integer> mList;

    public SplashView getSplashView() {
        return splashView;
    }

    public LinearLayout getDotLayout() {
        return dotLayout;
    }

    public SplashFrameLayout(@NonNull Context context) {
        this(context, null);
    }

    public SplashFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SplashFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSplashView();
        initDotView();
    }

    public void addData(List<Integer> mList, int width, int height) {
        L.e("addadata");
        this.mList = mList;
        for (int i = 0; i < mList.size(); i++) {
            ImageView img = new ImageView(getContext());
            img.setBackgroundResource(mList.get(i));
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(width, height);
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            img.setLayoutParams(lp);
            splashView.addView(img);
        }
        addDotLinearlayout();
    }

    private void addDotLinearlayout() {
        for (int i = 0; i < mList.size(); i++) {
            ImageView img = new ImageView(getContext());
            if (i == 0) {
                img.setBackgroundResource(R.drawable.dot_select);
            } else {
                img.setBackgroundResource(R.drawable.dot_no_select);
            }
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams lps = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lps.setMargins(8, 10, 0, 0);
            img.setLayoutParams(lps);
            dotLayout.addView(img);

        }
    }

    private void initDotView() {
        L.e("initDotView");
        dotLayout = new LinearLayout(getContext());
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
        lp.setLayoutDirection(LinearLayout.HORIZONTAL);
        lp.gravity = Gravity.BOTTOM;
        dotLayout.setLayoutParams(lp);
        dotLayout.setGravity(Gravity.CENTER);
        addView(dotLayout);


    }

    private void initSplashView() {
        splashView = new SplashView(getContext());
        splashView.setDotIndexListener(this);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        splashView.setLayoutParams(lp);
        addView(splashView);
    }

    @Override
    public void indexListener(int pos) {
        for (int i = 0; i < mList.size(); i++) {
            if (i == pos) {
                ImageView img = (ImageView) dotLayout.getChildAt(i);
                img.setBackgroundResource(R.drawable.dot_select);
            } else {
                ImageView img = (ImageView) dotLayout.getChildAt(i);
                img.setBackgroundResource(R.drawable.dot_no_select);
            }
        }

    }
}
