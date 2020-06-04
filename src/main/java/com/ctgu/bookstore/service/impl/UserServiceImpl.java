package com.ctgu.bookstore.service.impl;

import com.ctgu.bookstore.entity.User;
import com.ctgu.bookstore.mapper.UserMapper;
import com.ctgu.bookstore.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
