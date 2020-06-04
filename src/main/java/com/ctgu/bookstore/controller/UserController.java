package com.ctgu.bookstore.controller;


import com.ctgu.bookstore.entity.User;
import com.ctgu.bookstore.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xuzhen
 * @since 2020-06-03
 */
@RestController
@RequestMapping("/bookstore/user")
public class UserController {

    @Autowired
    private UserService userservice;

    @RequestMapping("/login/{email}/{password}")
    @ApiOperation("会员用邮箱和密码登录")

    public String userLogin(@PathVariable("email") String email,@PathVariable("password") String password){
        User user = userservice.getByEmail(email);
        String message;
        if(user == null)
           message = "用户不存在";
        else if(!user.getUserPassword().equals(password))
            message = "密码不正确";
        else
            message = "登录成功";
        return message;
    }

    @RequestMapping("/register/{user}")
    @ApiOperation("会员注册")
    public String register(@PathVariable("user") User user){
        User respondeUser = userservice.getByEmail(user.getEmail());
        String message;
        if(respondeUser != null)
            message = "该邮箱已被注册";
        else
        {
            userservice.save(user);
            message = "注册成功";
        }
        return message;
    }


}

