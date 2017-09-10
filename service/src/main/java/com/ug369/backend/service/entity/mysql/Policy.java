package com.ug369.backend.service.entity.mysql;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ug_policy")
public class Policy implements Serializable {
    private static final long serialVersionUID = -8566197653568234573L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long user;//用户
    private long platform;//市场
    private Date starttime;
    private Date endtime;
    private Double maxprice;//最高价格阈值
    private Double minprice;//最低价格阈值
    private Double amount;//每次交易量
    private Double initprice;//初始价格
    private Double rate;
    private Double balance;
    private boolean type;//0 by rate, 1 by balance

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public long getPlatform() {
        return platform;
    }

    public void setPlatform(long platform) {
        this.platform = platform;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Double getMaxprice() {
        return maxprice;
    }

    public void setMaxprice(Double maxprice) {
        this.maxprice = maxprice;
    }

    public Double getMinprice() {
        return minprice;
    }

    public void setMinprice(Double minprice) {
        this.minprice = minprice;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getInitprice() {
        return initprice;
    }

    public void setInitprice(Double initprice) {
        this.initprice = initprice;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }
}
