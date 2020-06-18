package com.ctgu.bookstore.utils;

import com.ctgu.bookstore.service.LikeService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: BookStore
 * @description:
 * @author: Linn
 * @create: 2020-06-18 17:26
 **/
public class LikeTask extends QuartzJobBean {
    @Autowired
    private LikeService likeService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        likeService.transLikeFromRedis();
    }

}
