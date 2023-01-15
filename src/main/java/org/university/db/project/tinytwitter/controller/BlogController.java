package org.university.db.project.tinytwitter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.university.db.project.tinytwitter.entity.Blog;
import org.university.db.project.tinytwitter.entity.search.BlogSearchRequest;
import org.university.db.project.tinytwitter.entity.web.Response;
import org.university.db.project.tinytwitter.service.BlogService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {
    public static final Logger LOGGER = LoggerFactory.getLogger(BlogController.class);

    private final BlogService blogService;

    protected BlogController(BlogService blogService) {
        super();
        this.blogService = blogService;
    }

    @RequestMapping(value = "/search")
    public Response<List<Blog>> getBlogs(@RequestBody(required = false) BlogSearchRequest request) {
        try {
            return Response.ok(blogService.searchBlog(request));
        } catch (Exception e) {
            LOGGER.error("Search blogs failed", e);
            return Response.error(e.toString());
        }
    }

    @RequestMapping(value = "/add")
    public Response<Void> addBlogs(@RequestBody Blog blog) {
        try {
            blogService.add(blog);
            return Response.ok(null);
        } catch (Exception e) {
            LOGGER.error("Add blogs failed", e);
            return Response.error(e.toString());
        }
    }

    @RequestMapping(value = "/update")
    public Response<Void> updateBlogs(@RequestBody Blog blog) {
        try {
            blog.setUpdateDate(new Date());
            blogService.update(blog);
            return Response.ok(null);
        } catch (Exception e) {
            LOGGER.error("Update blogs failed", e);
            return Response.error(e.toString());
        }
    }

    @RequestMapping(value = "/delete")
    public Response<Void> deleteBlogs(@RequestBody List<Integer> blogs) {
        try {
            blogService.deleteBlogs(blogs);
            return Response.ok(null);
        } catch (Exception e) {
            LOGGER.error("Delete blogs failed", e);
            return Response.error(e.toString());
        }
    }
}
