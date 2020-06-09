package com.ctgu.bookstore.service.impl;

import com.ctgu.bookstore.entity.Comment;
import com.ctgu.bookstore.mapper.CommentMapper;
import com.ctgu.bookstore.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Nidol
 * @since 2020-06-03
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
