package com.ctgu.bookstore.service.impl;

import com.ctgu.bookstore.entity.Comment;
import com.ctgu.bookstore.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

/**
 * @program: BookStore
 * @description:
 * @author: Linn
 * @create: 2020-06-18 15:42
 **/
@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private CommentServiceImpl commentService;

    @Override
    public Integer UserLikeBooks(String commentId) {
        ValueOperations<String, Integer> operations = redisTemplate.opsForValue();
        if( redisTemplate.hasKey(commentId) ){
           operations.set(commentId,
                   new Integer((Integer) operations.get(commentId).intValue() + 1));
        }else {
            Comment comment = commentService.getById(commentId);
            if(comment.getLikeCount() != null){
                operations.set(commentId,new Integer(comment.getLikeCount().intValue() + 1));
            }else{
                operations.set(commentId,1);
            }
        }
        return operations.get(commentId);
    }

    @Override
    public Integer UserNotLikeBooks(String commentId) {
        ValueOperations<String, Integer> operations = redisTemplate.opsForValue();
        if( redisTemplate.hasKey(commentId) ){
            operations.set(commentId,
                    new Integer((Integer) operations.get(commentId).intValue() - 1));
        }else {
            Comment comment = commentService.getById(commentId);
            if(comment.getLikeCount() != null){
                operations.set(commentId,new Integer(comment.getLikeCount().intValue() - 1));
            }else {
                operations.set(commentId,0);
            }
        }
        return operations.get(commentId);
    }

    @Override
    public void transLikeFromRedis() {
        ValueOperations<String, Integer> operations = redisTemplate.opsForValue();
        Set<String> keys = redisTemplate.keys("*");
        for (String key: keys
             ) {
            Long likeCount = (long)operations.get(key);
            Comment comment = commentService.getById(key);
            if(comment != null){
                comment.setLikeCount(likeCount);
                commentService.updateById(comment);
            }
        }
    }
}
