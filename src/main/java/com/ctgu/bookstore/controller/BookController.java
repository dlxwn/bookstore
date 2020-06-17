package com.ctgu.bookstore.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ctgu.bookstore.entity.Book;
import com.ctgu.bookstore.entity.Result;
import com.ctgu.bookstore.mapper.BookMapper;
import com.ctgu.bookstore.service.BookService;
import com.ctgu.bookstore.utils.ExcelUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/bookstore/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookMapper bookMapper;

    @PostMapping("/save")
    @ApiOperation("增加一条图书信息")
    public Result saveOneBook(@RequestBody Book book) {
        System.out.println(book);
        bookService.save(book);
        Result result = new Result();
        result.setCode(200);
        result.setMsg("添加成功");
        return result;
    }

    @DeleteMapping("/remove/{isbn}")
    @ApiOperation("删除指定图书的信息")
    public Result removeOneBook(@PathVariable("isbn") String isbn) {
        Result result = new Result();
        bookService.removeById(isbn);
        result.setCode(200);
        result.setMsg("删除成功");
        return result;
    }

    @GetMapping("/get/{isbn}")
    @ApiOperation("获取从前端传过来的ISBN，返回这本书的信息")
    public Book getOneBook(@PathVariable("isbn") String isbn) {
        Book book = bookService.getById(isbn);
        System.out.println(book);
        return book;
    }

    @PostMapping("/update")
    @ApiOperation("获取到指定图书，然后对其信息进行修改")
    public Result updateOneBook(@RequestBody Book book) {
        System.out.println("11111111111111111" + book);
        Result result = new Result();
        bookService.updateById(book);
        result.setCode(200);
        result.setMsg("更新成功");
        return result;
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
    @ApiOperation("图书名称、作者模糊查询")
    public List<Book> getFuzzy(@RequestParam("name") String name){
        List<Book> books = this.bookMapper.selectList(new QueryWrapper<Book>()
        .like("book_name", name).or().like("author", name));
        return books;
    }

    @GetMapping("/listPages/{page}/{size}")
    @ApiOperation("实现分页查询")
    public IPage<Book> getListPages(@PathVariable("page")  int page, @PathVariable("size") int size){
        System.out.println(" 页数" + page + "     " + "size");
        return bookService.getListPages(page, size);
    }

    @GetMapping("/listFuzzy/{fuzzy}/{page}/{size}")
    @ApiOperation("全字段模糊查询分页")
    public IPage<Book> getFuzzyPages(@PathVariable("fuzzy")String fuzzy,
                                     @PathVariable("page")int page,
                                     @PathVariable("size")int size){
        return bookService.getFuzzyPages(fuzzy, page, size);
    }

    /**
     * 导入用户数据
     * @return
     */
    @PostMapping("/import")
    @ApiOperation("批量导入Book信息")
    public String uploadExcel(@RequestParam("file") MultipartFile file,
                              Map<String, Object> map) {
        String name = file.getOriginalFilename();
        List<Book> list = null;
        try {
            list = ExcelUtil.excelToShopIdList(file.getInputStream());
            if (list == null || list.size() <= 0) {
                return "导入的数据为空";
            }
            //excel的数据保存到数据库
            try {
                for (Book excel : list) {
                    bookService.save(excel);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return e.getMessage();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
        return "保存成功";
    }
    @GetMapping("/exportBooks")
    @ApiOperation("导出Book信息到Excel中")
    public void exportBooks(HttpServletRequest request, HttpServletResponse response){
        try{
            List<Book> voList = bookService.list(null);
            String[] headerName = { "图书编号","书 名", "图 片", "作 者","价 格","简 介","类 型","库 存","出版社","收藏量","月销量","出版日期"};
            String[] headerKey = { "isbn","bookName", "bookPicture", "author","price","description","bookType","repertory","press","cllectNum","saleNum","publicDate"};
            HSSFWorkbook wb = ExcelUtil.createExcel(headerName, headerKey, "报名信息管理表", voList);
            if (wb == null) {
                return;
            }
            response.setContentType("application/vnd.ms-excel");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date = new Date();
            String str = sdf.format(date);
            String fileName = "学生信息管理" + str;
            response.setHeader("Content-disposition",
                    "attachment;filename=" + "Book信息表" + ".xls");
            OutputStream ouputStream = response.getOutputStream();
            ouputStream.flush();
            wb.write(ouputStream);
            ouputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

