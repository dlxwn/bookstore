package com.ctgu.bookstore.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctgu.bookstore.entity.Orderlist;
import com.ctgu.bookstore.service.OrderlistService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wby
 * @since 2020-06-03
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/bookstore/orderlist")
public class OrderlistController {

    @Autowired
    private OrderlistService orderlistService;

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除一个订单")
    public Boolean deleteOrderlist(@PathVariable("id") int orderId){
        Boolean res = orderlistService.removeById(orderId);
        return res;
    }

    @PutMapping("/update/{id}")
    @ApiOperation("订单审核，使订单状态变为true")
    public Boolean updateOrderlist(@PathVariable("id") int orderId){
        Orderlist orderlist = new Orderlist();
        orderlist.setOrderId(orderId);
        orderlist.setStatus(true);
        Boolean res = orderlistService.updateById(orderlist);
        return res;
    }

    @GetMapping("/select/{id}")
    @ApiOperation("根据订单编号查询订单")
    public Orderlist selectOneOrderlist(@PathVariable("id") int orderId){
        Orderlist oneOrder = orderlistService.getById(orderId);
        System.out.println(oneOrder);
        return oneOrder;
    }

    @GetMapping("/selectAll/{page}/{size}")
    @ApiOperation("显示所有订单，并分页显示")
    public IPage<Orderlist> selectAllOrderlist(@PathVariable("page")int page, @PathVariable("size")int size){
        return orderlistService.getAll(page,size);
    }

    @GetMapping("/selectList/{condition}")
    @ApiOperation("根据地址、收货人、电话查询")
    public List<Orderlist> selectOrderlist(@PathVariable("condition")String fuzzy){
        List<Orderlist> order =  orderlistService.getListByFuzzy(fuzzy);
        return order;
    }
}

