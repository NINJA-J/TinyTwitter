package org.university.db.project.tinytwitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.university.db.project.tinytwitter.entity.Comment;
import org.university.db.project.tinytwitter.entity.search.CommentSearchRequest;
import org.university.db.project.tinytwitter.entity.web.Response;
import org.university.db.project.tinytwitter.service.CommentService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    protected CommentController(CommentService commentService) {
        super();
        this.commentService = commentService;
    }

    @RequestMapping("/search")
    public Response<List<Comment>> searchComment(@RequestBody CommentSearchRequest request) {
        if (request.getBlogId() == null) {
            return Response.error("Missing Blog Id");
        }
        try {
            return Response.ok(commentService.searchComments(request));
        } catch (Exception e) {
            return Response.error("Query Comments Failed, " + e);
        }
    }

    @RequestMapping("/add")
    public Response<Void> addComment(@RequestBody Comment comment) {
        comment.setCreateDate(new Date());
        comment.setUpdateDate(comment.getCreateDate());
        try {
            commentService.add(comment);
            return Response.ok(null);
        } catch (Exception e) {
            return Response.error(e.toString());
        }
    }

    @RequestMapping("/update")
    public Response<Void> updateComment(@RequestBody Comment comment) {
        comment.setUpdateDate(new Date());
        try {
            commentService.update(comment);
            return Response.ok(null);
        } catch (Exception e) {
            return Response.error(e.toString());
        }
    }

    @RequestMapping("/delete/{id}")
    public Response<Void> deleteComment(@PathVariable("id") Integer id) {
        try {
            commentService.deleteById(id);
            return Response.ok(null);
        } catch (Exception e) {
            return Response.error(e.toString());
        }
    }
}
