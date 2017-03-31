package com.gsww.www.accountbook;

import android.app.Application;

import com.dao.DaoMaster;
import com.dao.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Author : luweicheng on 2017/3/27 0027 10:34
 * E-mail :1769005961@qq.com
 * GitHub : https://github.com/luweicheng24
 */

public class MyApplication extends Application{

    private static MyApplication instance;

    private DaoSession daoSession;

    public DaoSession getDaoSession() {

        return daoSession;
    }
    public static final String DB_NAME = "accounts.db";
    @Override
    public void onCreate() {
        super.onCreate();
        setUpGreenDao();
        instance = this;
    }
    public MyApplication(){

    }

    /**
     * 单例模式获取MyApplication的实例
     * @return
     */
    public static MyApplication getInstance(){

            synchronized (MyApplication.class){
               return instance;
            }

    }

    private void setUpGreenDao() {
        // 常见数据库打开工具类相当于SqliteOpenHelper
        DaoMaster.DevOpenHelper dm  = new DaoMaster.DevOpenHelper(this,DB_NAME);
        //获取可读可写的数据库
        Database db = dm.getWritableDb();
        DaoMaster.createAllTables(db,true);
        //创建一个管理该数据库的对象
        DaoMaster  daoMaster = new DaoMaster(db);
        //创建一个管理表的对象
        daoSession = daoMaster.newSession();


    }
}
