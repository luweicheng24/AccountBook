package com.gsww.www.accountbook;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.gsww.com.accountbook.R;
import com.gsww.www.accountbook.bean.User;
import com.gsww.www.accountbook.utils.SharePrefenceUtils;
import com.gsww.www.accountbook.view.SplashFrameLayout;
import com.gsww.www.accountbook.view.SplashView;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : luweicheng on 2017/3/28 0028 09:17
 * E-mail ：1769005961@qq.com
 * GitHub : https://github.com/luweicheng24
 */

public class SplashActivity extends AppCompatActivity implements SplashView.OnStartMainListener {
    private SplashFrameLayout splash;
    private List<Integer>  mList;
    private int width;
    private int height;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_splash);
        splash = (SplashFrameLayout) findViewById(R.id.splash);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
        height = dm.heightPixels;
        mList = new ArrayList<>();
        mList.add(R.drawable.splash_6);
        mList.add(R.drawable.splash_7);
        mList.add(R.drawable.splash_5);
        splash.addData(mList,width,height);
        splash.getSplashView().setOnStartMainListener(this);

    }

    @Override
    public void onClick() {
        User user = SharePrefenceUtils.getInstance(this).getLogin();
        if(user!=null){
            startActivity(new Intent(this,MainActivity.class));

        }else{
            startActivity(new Intent(this,LoginActivity.class));

        }
        finish();
    }
}
