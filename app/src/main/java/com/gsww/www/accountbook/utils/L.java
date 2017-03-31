package com.gsww.www.accountbook.utils;

/**
 * Author : 卢卫成 on 2017/3/27 0027 16:27
 * E-mail ：1769005961@qq.com
 * GitHub : https://github.com/luweicheng24
 */

import android.util.Log;

/**
 * 自定义Log类
 */
public class L {
    public static final Boolean IS_DEBUG = true;
    public static final String TAG = "ACCOUNTSBOOK";
    public static void e(String message){
        Log.e(TAG, "e: "+message );
    }

}
