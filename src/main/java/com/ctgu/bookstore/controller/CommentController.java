package com.ctgu.bookstore.controller;


import com.ctgu.bookstore.entity.Comment;
import com.ctgu.bookstore.service.CommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Nidol
 * @since 2020-06-03
 */
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/bookstore/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/test/{id}")
    @ApiOperation("获取一个评论")
    public Comment getOneComment(@PathVariable("id") int Commentid){
        Comment comment =commentService.getById(Commentid);
        System.out.println(comment);
        return comment;
    }
}

