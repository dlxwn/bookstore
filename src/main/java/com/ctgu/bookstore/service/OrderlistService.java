package com.ctgu.bookstore.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctgu.bookstore.entity.Orderlist;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wby
 * @since 2020-06-03
 */
public interface OrderlistService extends IService<Orderlist> {


    List<Orderlist> getListByFuzzy(String fuzzy);

    IPage<Orderlist> getAll(int page, int size);
}
