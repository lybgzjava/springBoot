package com.example.springboot.service;

import com.example.springboot.dao.LoginLogDao;
import com.example.springboot.dao.UserDao;
import com.example.springboot.domain.LoginLog;
import com.example.springboot.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by silivar on 2018/9/16.
 */

@Service//将UserService标注为一个服务层的Bean
public class UserService {
    private UserDao userDao;
    private LoginLogDao loginLogDao;

    @Autowired
    public void setUserDao(UserDao userDao){
        this.userDao=userDao;
    }

    @Autowired
    public void setLoginLogDao(LoginLogDao loginLogDao){
        this.loginLogDao=loginLogDao;
    }

    //hasMatchUser（）方法，用于检查用户名、密码是否正确

    public boolean hasMatchUser(String userName,String password){
        int matchCount=userDao.getMatchCount(userName, password);
        return matchCount>0;
    }

    //findUserByUserName（）方法，以用户名为条件加载User对象

    public  User findUserByUserName(String userName){
        return userDao.findUserByUserName(userName);
    }

    //用户登陆成功后调用
    @Transactional
    public void loginSuccess(User user){
        user.setCredits((5+user.getCredits()));
        LoginLog loginLog=new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate((user.getLastVisit()));
        userDao.updateLoginInfo(user);
        loginLogDao.insertLoginLog(loginLog);
    }
}
