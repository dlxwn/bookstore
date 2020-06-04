package com.ctgu.bookstore.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.ctgu.bookstore.entity.User;
import com.ctgu.bookstore.mapper.UserMapper;
import com.ctgu.bookstore.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/find/{id}")
    @ApiOperation("根据id查找用户")
    public User getById(@PathVariable("id") int id){
       return userService.getById(id);
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
    public Boolean edit(@PathVariable("id") int id) {
        return userService.removeById(id);
    }

    @PutMapping("/edit")
    @ApiOperation("更新用户")
    public Boolean editUser(@RequestBody User user) {
        return userService.updateById(user);
    }
}

