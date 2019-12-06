package com.example.springboot.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by silivar on 2018/9/15.
 */
public class LoginLog implements Serializable{
    private int loginLogId;
    private int userId;
    private String ip;
    private Date loginDate;

    //以下是相应的get/set方法



    public int getLoginLogId() {
        return loginLogId;
    }

    public void setLoginLogId(int loginLogId) {
        this.loginLogId = loginLogId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }
}
