package com.ctgu.bookstore.mapper;

import com.ctgu.bookstore.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xuzhen
 * @since 2020-06-03
 */
public interface EmployeeMapper extends BaseMapper<Employee> {
    String getPassword(String email);

    String getRole(String email);
}
