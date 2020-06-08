package com.ctgu.bookstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctgu.bookstore.entity.Orderlist;
import com.ctgu.bookstore.mapper.OrderlistMapper;
import com.ctgu.bookstore.service.OrderlistService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wby
 * @since 2020-06-03
 */
@Service
public class OrderlistServiceImpl extends ServiceImpl<OrderlistMapper, Orderlist> implements OrderlistService {

    @Autowired
    private OrderlistMapper orderlistMapper;
    @Override
    public List<Orderlist> getListByFuzzy(String fuzzy) {
        QueryWrapper<Orderlist> qw = new QueryWrapper<>();
        qw.like("order_address", fuzzy).or()
                .like("order_tel", fuzzy).or()
                .like("order_name", fuzzy);
        List<Orderlist> order = orderlistMapper.selectList(qw);
        return order;
    }

    @Override
    public IPage<Orderlist> getAll(int page, int size) {
        IPage<Orderlist> orderIPage = orderlistMapper.selectPage(new Page<>(page,size), null);
        return orderIPage;
    }

}
