package com.ctgu.bookstore.service;

import com.ctgu.bookstore.entity.Orderdetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wby
 * @since 2020-06-10
 */
public interface OrderdetailService extends IService<Orderdetail> {

    List<Orderdetail> getListById(int id);

}
