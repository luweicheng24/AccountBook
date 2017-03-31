package com.gsww.www.accountbook.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.gsww.www.accountbook.bean.User;

import static android.content.Context.MODE_PRIVATE;

/**
 * Author : luweicheng on 2017/3/30 0030 09:12
 * E-mail ：1769005961@qq.com
 * GitHub : https://github.com/luweicheng24
 */

public class SharePrefenceUtils {
    static SharePrefenceUtils sp = null ;

    public static final String LOGIN = "loginMessage";
    public static SharedPreferences sharedPreferences;
    public static final String PHONE = "phone";
    public static final String PASSWORD = "password";
    private SharePrefenceUtils(Context context){
        sharedPreferences = context.getSharedPreferences(LOGIN,MODE_PRIVATE);
    }

    public static SharePrefenceUtils getInstance(Context context){
        if(sp == null){
            synchronized (SharePrefenceUtils.class){
                if(sp == null){
                    sp = new SharePrefenceUtils(context);
                }

            }
        }
         return sp;
    }

    public  Boolean writeLoginMessage(String phone,String password){
        SharedPreferences.Editor editor =  sharedPreferences.edit();
        editor .putString(PHONE,phone);
        editor .putString(PASSWORD,password);
        Boolean isSuccess = editor.commit();
        if(isSuccess ){
            return  true;
        }else{
            return false;
        }
    }

    public  User getLogin(){
       String phone =  sharedPreferences.getString(PHONE,"null");
        String password = sharedPreferences.getString(PASSWORD,"null");
        if(!phone.equals("null")||!password.equals("null")){
            return new User(phone,password);
        }
        return null;
    }
}
