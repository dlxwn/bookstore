package com.ctgu.bookstore.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @program: BookStore
 * @description:
 * @author: Linn
 * @create: 2020-06-06 10:37
 **/
@EnableTransactionManagement(proxyTargetClass = true)
@Configuration
public class MybatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
