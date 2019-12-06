package com.example.springboot.web;

import com.example.springboot.domain.User;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by silivar on 2018/9/18.
 */
//1.标注成为一个spring mvc的Controller

@Controller
public class LoginController {
    private UserService userService;

    /**
     *   2.负责处理/index.html的请求
     */
    @RequestMapping(value = "/index.html")
    public String loginPage() {
        System.out.println("index.html");
        return "login";
    }

    /**
     *    3.负责处理/loginCheck.html的请求
     */
    @RequestMapping(value = "/loginCheck.html")
    public ModelAndView loginCheck(HttpServletRequest request, LoginCommand loginCommand) {
        boolean isValidUser = userService.hasMatchUser(loginCommand.getUserName(), loginCommand.getPassword());
        if (!isValidUser) {
            return new ModelAndView("login", "error", "用户名或密码错误。");
        } else {
            User user = userService.findUserByUserName(loginCommand.getUserName());
            user.setLastIp(request.getLocalAddr());
            user.setLastVisit(new Date());
            userService.loginSuccess(user);
            request.getSession().setAttribute("user", user);
            return new ModelAndView("main");

        }
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;

    }
}
