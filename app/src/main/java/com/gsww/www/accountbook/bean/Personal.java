package com.gsww.www.accountbook.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Author : luweicheng on 2017/3/29 0029 09:08
 * E-mail ：1769005961@qq.com
 * GitHub : https://github.com/luweicheng24
 */
@Entity
public class Personal {

    @Override
    public String toString() {
        return "Personal{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Id(autoincrement = true)
    private Long id;
    @Property(nameInDb = "PHONE")
    private String phone;
    @Property(nameInDb = "PASSWORD")
    private String password;
    @Generated(hash = 1636901736)
    public Personal(Long id, String phone, String password) {
        this.id = id;
        this.phone = phone;
        this.password = password;
    }
    @Generated(hash = 278967090)
    public Personal() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
