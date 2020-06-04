package com.ctgu.bookstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ctgu.bookstore.entity.User;
import com.ctgu.bookstore.mapper.UserMapper;
import com.ctgu.bookstore.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xuzhen
 * @since 2020-06-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper usermapper ;

    @Override
    public User getByEmail(String email) {
        User userCondition = new User();
        userCondition.setEmail(email);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.setEntity(userCondition);
        User user = usermapper.selectOne(wrapper);
        return user;
    }
}
