package com.ctgu.bookstore.service;

import com.ctgu.bookstore.entity.Bookclassify;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wby
 * @since 2020-06-16
 */
public interface BookclassifyService extends IService<Bookclassify> {
    List<String> listClassName();
}
