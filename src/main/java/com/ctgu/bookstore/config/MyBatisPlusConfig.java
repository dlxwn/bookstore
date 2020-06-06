package com.ctgu.bookstore.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * @program: BookStore
 * @description:
 * @author: Linn
 * @create: 2020-06-06 10:37
 **/
public class MyBatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
