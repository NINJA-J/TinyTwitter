package org.university.db.project.tinytwitter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.university.db.project.tinytwitter.entity.Blog;
import org.university.db.project.tinytwitter.entity.search.BlogSearchRequest;
import org.university.db.project.tinytwitter.entity.web.Response;
import org.university.db.project.tinytwitter.service.BlogService;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {

    private final BlogService blogService;

    protected BlogController(BlogService blogService) {
        super();
        this.blogService = blogService;
    }

    @RequestMapping(value = "/search")
    public @ResponseBody
    Response<List<Blog>> getBlogs(@RequestBody(required = false) BlogSearchRequest request) {
        try {
            return Response.ok(blogService.searchBlog(request));
        } catch (Exception e) {
            return Response.error(e.toString());
        }
    }

    @RequestMapping(value = "/add")
    public @ResponseBody
    Response<Void> addBlogs(@RequestBody Blog blog) {
        try {
            blogService.add(blog);
            return Response.ok(null);
        } catch (Exception e) {
            return Response.error(e.toString());
        }
    }

    @RequestMapping(value = "/update")
    public @ResponseBody
    Response<Void> updateBlogs(@RequestBody Blog blog) {
        try {
            blog.setUpdateDate(new Date());
            blogService.update(blog);
            return Response.ok(null);
        } catch (Exception e) {
            return Response.error(e.toString());
        }
    }

    @RequestMapping(value = "/delete")
    public @ResponseBody
    Response<Void> deleteBlogs(@RequestBody List<Integer> blogs) {
        try {
            blogService.deleteBlogs(blogs);
            return Response.ok(null);
        } catch (Exception e) {
            return Response.error(e.toString());
        }
    }
}
