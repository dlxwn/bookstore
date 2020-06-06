package com.ctgu.bookstore.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ctgu.bookstore.entity.Employee;
import com.ctgu.bookstore.entity.User;
import com.ctgu.bookstore.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: bookstore
 * @description:
 * @author: Linn
 * @create: 2020-06-2 16:05
 **/
@RestController
@RequestMapping("/bookstore/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

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

