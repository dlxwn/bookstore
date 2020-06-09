package com.ctgu.bookstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ctgu.bookstore.entity.Employee;
import com.ctgu.bookstore.mapper.EmployeeMapper;
import com.ctgu.bookstore.service.EmployeeService;
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
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Employee getByEmail(String email) {
        Employee employeeCondition = new Employee();
        employeeCondition.setEmail(email);
        QueryWrapper<Employee> qw = new QueryWrapper<>();
        qw.setEntity(employeeCondition);
        Employee employee = employeeMapper.selectOne(qw);
        return employee;
    }
}
