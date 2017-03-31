package com.gsww.www.accountbook.utils;

import com.dao.AccountsBeanDao;
import com.dao.PersonalDao;
import com.gsww.www.accountbook.MyApplication;
import com.gsww.www.accountbook.bean.AccountsBean;
import com.gsww.www.accountbook.bean.Personal;

import java.util.List;

/**
 * Author : luweicheng on 2017/3/27 0027 16:15
 * E-mail ：1769005961@qq.com
 * GitHub : https://github.com/luweicheng24
 */

/**
 * 数据管理类
 */
public class DataManager {

    public static AccountsBeanDao dao = MyApplication.getInstance().getDaoSession().getAccountsBeanDao();
    public static PersonalDao personDao = MyApplication.getInstance().getDaoSession().getPersonalDao();

    public static Boolean insertAccount(AccountsBean accountsBean) {

        Long result = dao.insertOrReplace(accountsBean);
        if(result!=null){
            L.e("插入成功id=" + result);
            return true;
        }else{
            return false;
        }
    }

    public static void deleteAccount(String data) {
        List<AccountsBean> mList = dao.queryBuilder().where(AccountsBeanDao.Properties.CostDate.eq(data)).list();
        long id = mList.get(0).getId();
        dao.deleteByKey(id);
        L.e("删除成功");
    }

    public static List<AccountsBean> queryAccountList() {
        List<AccountsBean> listAcc = dao.queryBuilder().list();
        L.e("查询成功=="+listAcc.size());
        System.out.println("查询成功=="+listAcc.size());
        return listAcc;
    }

    public static Boolean login(Personal personal) {
        org.greenrobot.greendao.query.Query<Personal> query = personDao.queryBuilder().where(
                  PersonalDao.Properties.Phone.eq(personal.getPhone())
                , PersonalDao.Properties.Password.eq(personal.getPassword())).limit(1).build();
        if (query.list().size() == 0) {

            return false;
        } else {
            return true;
        }
    }

    public static Boolean regist(Personal person) {
        Boolean isExist = personDao.hasKey(person);
        if (!isExist) {
            personDao.insert(person);
            L.e("注册成功");
            return true;
        } else {
            return false;
        }
    }
}
