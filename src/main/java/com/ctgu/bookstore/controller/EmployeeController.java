package com.ctgu.bookstore.controller;


import com.ctgu.bookstore.entity.Employee;
import com.ctgu.bookstore.entity.Result;
import com.ctgu.bookstore.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ctgu.bookstore.entity.Employee;
import com.ctgu.bookstore.entity.User;
import com.ctgu.bookstore.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.UUID;
import java.util.List;

/**
 * @program: bookstore
 * @description:
 * @author: Linn
 * @create: 2020-06-2 16:05
 **/
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

    @GetMapping("/find/{id}")
    @ApiOperation("根据id查找职员")
    public Employee getById(@PathVariable("id") int id){
        return employeeService.getById(id);
    }

    @GetMapping("/findAll/{page}/{size}")
    @ApiOperation("查找所有用户，实现分页")
    public IPage<Employee> getAll(@PathVariable("page") int page, @PathVariable("size") int size){
        return employeeService.getAll(page,size);
    }

    @GetMapping("/findList/{field}")
    @ApiOperation("字段模糊查询")
    public List<Employee> getListEmployeeByFuzzy(@PathVariable("field") String field){
        return employeeService.getListUserByFuzzy(field);
    }

    @PostMapping("/findByRequest")
    @ApiOperation("条件查询，实现分页，默认从第一页显示，每页显示10条记录")
    public IPage<Employee> getAllByRequest(@RequestBody Employee query){
        return employeeService.getAllByRequest(query);
    }

    @PostMapping("/add")
    @ApiOperation("添加职员")
    public Boolean addEmployee(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation("删除职员")
    public Boolean delEmployee(@PathVariable("id") int id) {
        return employeeService.removeById(id);
    }

    @PutMapping("/edit")
    @ApiOperation("更新职员")
    public Boolean editEmployee(@RequestBody Employee employee) {
        return employeeService.updateById(employee);
    }

}
