package com.gsww.www.accountbook.bean;

/**
 * Author : luweicheng on 2017/3/30 0030 09:37
 * E-mail ：1769005961@qq.com
 * GitHub : https://github.com/luweicheng24
 */

public class User {
    public User(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public String getPhone() {

        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String phone;
    private String password;
}
