package com.ctgu.bookstore.controller;


import com.ctgu.bookstore.entity.Employee;
import com.ctgu.bookstore.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Nidol
 * @since 2020-06-03
 */
@RestController
@RequestMapping("/bookstore/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/employeeLogin/{email}/{password}")
    @ApiOperation("职员用邮箱和密码登录")
    public String employeeLogin(@PathVariable("email") String email, @PathVariable("password") String password){
        Employee employee = employeeService.getByEmail(email);
        String message;
        if (employee == null){
            message = "用户不存在";
        }else if (!employee.getEmpPassword().equals(password)){
            message = "密码不正确！";
        }else{
            message = "登录成功！";
        }
        return message;
    }
}




















