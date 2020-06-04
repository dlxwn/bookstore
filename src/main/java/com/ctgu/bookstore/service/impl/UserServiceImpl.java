package com.ctgu.bookstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ctgu.bookstore.entity.User;
import com.ctgu.bookstore.mapper.UserMapper;
import com.ctgu.bookstore.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @program: bookstore
 * @description:
 * @author: Linn
 * @create: 2020-06-2 16:05
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getListUserByFuzzy(String field) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("name",field).or()
                .like("email",field).or()
                .like("user_id",field).or()
                .like("nick_name",field).or()
                .like("adress",field);
        List<User> users = userMapper.selectList(wrapper);
        return users;
    }
}
