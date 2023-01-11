package org.university.db.project.tinytwitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.university.db.project.tinytwitter.entity.Comment;
import org.university.db.project.tinytwitter.entity.search.CommentSearchRequest;
import org.university.db.project.tinytwitter.entity.search.CommentSearchResponse;
import org.university.db.project.tinytwitter.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    protected CommentController(CommentService commentService) {
        super();
        this.commentService = commentService;
    }

    @RequestMapping("/search")
    public @ResponseBody
    CommentSearchResponse searchComment(@RequestBody CommentSearchRequest request) {
        CommentSearchResponse response = new CommentSearchResponse();
        if (request.getBlogId() == null) {
            response.setStatus(2);
            response.setMessage("Missing Blog Id");
            return response;
        }
        try {
            response.setResults(commentService.searchComments(request));
            response.setStatus(0);
        } catch (Exception e) {
            response.setStatus(1);
            response.setMessage("Query Comments Failed, " + e.getMessage());
        }

        return response;
    }

    @RequestMapping("/add")
    public void addComment(@RequestBody Comment comment) {
        commentService.add(comment);
    }

    @RequestMapping("/update")
    public void updateComment(@RequestBody Comment comment) {
        commentService.update(comment);
    }

    @RequestMapping("/delete/{id}")
    public void deleteComment(@PathVariable("id") Integer id) {
        commentService.deleteById(id);
    }
}
