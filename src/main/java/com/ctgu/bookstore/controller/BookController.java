package com.ctgu.bookstore.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctgu.bookstore.entity.Book;
import com.ctgu.bookstore.mapper.BookMapper;
import com.ctgu.bookstore.service.BookService;
import com.ctgu.bookstore.utils.ExcelExport;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Nidol
 * @since 2020-06-03
 */
@RestController
@RequestMapping("/bookstore/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookMapper bookMapper;

    @PostMapping("/save")
    @ApiOperation("增加一条图书信息")
    public boolean saveOneBook(@ModelAttribute Book book, BindingResult bindingResult) {
        System.out.println(book);
        return bookService.save(book);
    }

    @DeleteMapping("/remove/{isbn}")
    @ApiOperation("删除指定图书的信息")
    public boolean removeOneBook(@PathVariable("isbn") String isbn) {
        System.out.println(isbn);
        return bookService.removeById(isbn);
    }

    @GetMapping("/get/{isbn}")
    @ApiOperation("获取从前端传过来的ISBN，返回这本书的信息")
    public Book getOneBook(@PathVariable("isbn") String isbn) {
        Book book = bookService.getById(isbn);
        System.out.println(book);
        return book;
    }

    @PutMapping("/update")
    @ApiOperation("获取到指定图书的图书编码，然后对其信息进行修改")
    public boolean updateOneBook(@RequestBody Book book) {
        return bookService.updateById(book);
    }

    @GetMapping("/listBook")
    @ApiOperation("查询所有图书的信息")
    public List<Book> listBooks() {
        List<Book> books = bookMapper.selectList(null);
        for (Book map : books) {
            System.out.println(map);
        }
        return books;
    }

    @GetMapping("/getFuzzy")
    @ApiOperation("全字段模糊查询")
    public List<Book> getFuzzy(@RequestParam("name") String name){
        List<Book> books = this.bookMapper.selectList(new QueryWrapper<Book>().like("ISBN", name).or()
        .like("book_name", name).or().like("author", name).or().like("price", name).or()
        .like("description", name).or().like("book_type", name).or().like("repertory", name).or()
        .like("press", name).or().like("cllect_num", name).or().like("sale_num", name).or()
        .like("public_date", name));
        return books;
    }


//    @GetMapping("/importExcel")
//    @ApiOperation("将图书信息导出到excel表中")
//    public void NurserExprotExcel(HttpServletResponse response){
//        String[] arr = new String[]{"ISBN", "book_name", "author", "price", "description", "book_type", "repertory", "press", "cllect_num", "sale_num"};
//        ExcelExport.export(response,bookMapper.selectList(null),arr);
//    }

    @GetMapping("/listPages")
    @ApiOperation("实现分页查询")
    public IPage<Map<String, Object>> selectPages(){
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        Page<Book> page = new Page<>(1,10);
        //IPage<User> userIPage = userMapper.selectPage(page, wrapper);
        IPage<Map<String, Object>> mapIPage = bookMapper.selectMapsPage(page, queryWrapper);
        System.out.println("总页数"+mapIPage.getPages());
        System.out.println("总记录数"+mapIPage.getTotal());
        List<Map<String, Object>> records = mapIPage.getRecords();
        records.forEach(System.out::println);
        return mapIPage;
    }
    // 方法二、自定义sql进行查询
//    @GetMapping("/listPages")
//    @ApiOperation("实现分页查询")
//    public IPage<Book> selectPages(){
//        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
//        Page<Book> page = new Page<>(1,10);
//        IPage<Book> userIPage = bookMapper.selectPage(page, queryWrapper);
//        System.out.println("总页数"+userIPage.getPages());
//        System.out.println("总记录数"+userIPage.getTotal());
//        List<Book> bookList = userIPage.getRecords();
//        bookList.forEach(System.out::println);
//        return userIPage;
//    }
    @GetMapping("/listPages/{page}")
    @ApiOperation("实现页面跳转")
    public IPage<Book> getListPages(@PathVariable("page") int page){
        int size = 10;
        return bookService.getListPages(page, size);
    }


}

