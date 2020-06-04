package com.ctgu.bookstore.service;

import com.ctgu.bookstore.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xuzhen
 * @since 2020-06-03
 */
public interface UserService extends IService<User> {
    List<User> getListUserByFuzzy(String field);
}
