package com.ctgu.bookstore.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ctgu.bookstore.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ctgu.bookstore.entity.User;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xuzhen
 * @since 2020-06-03
 */
public interface EmployeeService extends IService<Employee> {

    Employee getByEmail(String email);
    IPage<Employee> getAll(int page, int size);

    List<Employee> getListUserByFuzzy(String field);

    IPage<Employee> getAllByRequest(Employee query);

}
