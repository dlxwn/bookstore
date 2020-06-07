package com.ctgu.bookstore.controller;


import com.ctgu.bookstore.entity.Employee;
import com.ctgu.bookstore.entity.Result;
import com.ctgu.bookstore.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xuzhen
 * @since 2020-06-03
 */
//解决跨域问题
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/bookstore/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private HttpSession session;

    @GetMapping("/employeeLogin/{email}/{password}")
    @ApiOperation("职员通过邮箱密码登陆")
    public Result employeeLogin(@PathVariable("email")String email,
                                @PathVariable("password")String password){
        Result res = new Result();
        Employee employee = employeeService.getByEmail(email);
        if(employee == null){
            res.setCode(0);
            res.setMsg("用户不存在");
            return res;
        }

        if(!employee.getEmpPassword().equals(password)){
            res.setCode(0);
            res.setMsg("密码错误");
            return res;
        }
        res.setCode(1);
        res.setMsg("登陆成功");
        res.setData(employee);
        session.setAttribute("employee",employee);
        res.setToken(UUID.randomUUID().toString());
        return res;
    }

    @GetMapping("/employeeLogout")
    @ApiOperation("实现职员的退出")
    public void employeeLogout(){
        session.invalidate();
    }
}
