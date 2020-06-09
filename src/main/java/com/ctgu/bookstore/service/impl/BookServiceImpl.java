package com.ctgu.bookstore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctgu.bookstore.entity.Book;
import com.ctgu.bookstore.mapper.BookMapper;
import com.ctgu.bookstore.service.BookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Nidol
 * @since 2020-06-03
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public boolean updateById(Book book) {
        Book book1 = this.getById(book.getIsbn());
        if (book1 != null){
            book1.setBookName(book.getBookName());
            book1.setBookPicture(book.getBookPicture());
            book1.setAuthor(book.getAuthor());
            book1.setPrice(book.getPrice());
            book1.setDescription(book.getDescription());
            book1.setBookType(book.getBookType());
            book1.setRepertory(book.getRepertory());
            book1.setPress(book.getPress());
            book1.setCllectNum(book.getCllectNum());
            book1.setSaleNum(book.getSaleNum());
            book1.setPublicDate(book.getPublicDate());
            return this.save(book1);
        }
        return false;
    }

    @Override
    public IPage<Book> getListPages(int page, int size) {
        IPage<Book> userIPage = bookMapper.selectPage(new Page<>(page, size), null);
        return userIPage;
    }


}
