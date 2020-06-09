package com.ctgu.bookstore.controller;


import com.ctgu.bookstore.entity.Result;
import com.ctgu.bookstore.entity.User;
import com.ctgu.bookstore.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.UUID;

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
    private UserService userService;

    @Autowired
    private HttpSession session;

    @GetMapping("/userLogin/{email}/{password}")
    @ApiOperation("会员通过邮箱密码登陆")
    public Result userLogin(@PathVariable("email")String email,
                            @PathVariable("password")String password){
        Result res = new Result();
        User user = userService.getByEmail(email);
        if(user == null){
            res.setCode(0);
            res.setMsg("用户不存在");
            return res;
        }
        if(!user.getUserPassword().equals(password)){
            res.setCode(0);
            res.setMsg("密码错误");
            return res;
        }
        res.setCode(1);
        res.setMsg("登陆成功");
        res.setData(user);
        session.setAttribute("user", user);
        String token = UUID.randomUUID().toString();
        res.setToken(token);
        return res;
    }

    @PostMapping("/register")
    @ApiOperation("会员注册")
    public Result register(@RequestBody User newUser){
        Result res = new Result();
        User user = userService.getByEmail(newUser.getEmail());
        if(user!=null){
            res.setCode(0);
            res.setMsg("该邮箱已存在");
            return res;
        }
        userService.save(newUser);
        res.setCode(1);
        res.setMsg("注册成功");
        res.setData(newUser);
        return res;
    }

    @GetMapping("/userLogout")
    @ApiOperation("用户的退出")
    public void userLogout(){
        session.invalidate();
    }
}

