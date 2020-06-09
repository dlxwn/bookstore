package com.ctgu.bookstore.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ctgu.bookstore.entity.User;
import com.ctgu.bookstore.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.ctgu.bookstore.entity.Result;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @program: bookstore
 * @description:
 * @author: Linn
 * @create: 2020-06-2 16:05
 **/
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

    @GetMapping("/find/{id}")
    @ApiOperation("根据id查找用户")
    public User getById(@PathVariable("id") int id){
        return userService.getById(id);
    }

    @GetMapping("/findAll/{page}/{size}")
    @ApiOperation("查找所有用户，实现分页")
    public IPage<User> getAll(@PathVariable("page") int page, @PathVariable("size") int size){
        return userService.getAll(page,size);
    }

    @PostMapping("/findByRequest")
    @ApiOperation("条件查询，实现分页，默认从第一页显示，每页显示10条记录")
    public IPage<User> getAllByRequest(@RequestBody User query){
        return userService.getAllByRequest(query);
    }

    @GetMapping("/findList/{field}")
    @ApiOperation("字段模糊查询")
    public List<User> getListUserByFuzzy(@PathVariable("field") String field){
        return userService.getListUserByFuzzy(field);
    }

    @PostMapping("/add")
    @ApiOperation("添加用户")
    public Boolean addUser(@RequestBody User user){
        return userService.save(user);
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation("删除用户")
    public Boolean delUser(@PathVariable("id") int id) {
        return userService.removeById(id);
    }

    @PutMapping("/edit")
    @ApiOperation("更新用户")
    public Boolean editUser(@RequestBody User user) {
        return userService.updateById(user);
    }
}

