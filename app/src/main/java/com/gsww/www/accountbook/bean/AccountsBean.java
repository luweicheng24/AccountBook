package com.gsww.www.accountbook.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Author : 卢卫成 on 2017/3/27 0027 09:46
 * E-mail ：1769005961@qq.com
 * GitHub : https://github.com/luweicheng24
 *
 */

/**
 * 账单实体类
 */
@Entity
public class AccountsBean {
    @Override
    public String toString() {
        return "AccountsBean{" +
                "id=" + id +
                ", costName='" + costName + '\'' +
                ", costDate='" + costDate + '\'' +
                ", costPrice=" + costPrice +
                '}';
    }

    @Id(autoincrement = true )
    private Long id;
    @Property(nameInDb = "COST_NAME")
    private String costName;
    @Property(nameInDb = "COST_DATE")
    private String costDate;
    @Property(nameInDb = "COST_PRICE")
    private Long costPrice;
    @Generated(hash = 837093221)
    public AccountsBean(Long id, String costName, String costDate, Long costPrice) {
        this.id = id;
        this.costName = costName;
        this.costDate = costDate;
        this.costPrice = costPrice;
    }
    @Generated(hash = 1165329793)
    public AccountsBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCostName() {
        return this.costName;
    }
    public void setCostName(String costName) {
        this.costName = costName;
    }
    public String getCostDate() {
        return this.costDate;
    }
    public void setCostDate(String costDate) {
        this.costDate = costDate;
    }
    public Long getCostPrice() {
        return this.costPrice;
    }
    public void setCostPrice(Long costPrice) {
        this.costPrice = costPrice;
    }

}
