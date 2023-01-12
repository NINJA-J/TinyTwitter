package org.university.db.project.tinytwitter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.university.db.project.tinytwitter.entity.Blog;
import org.university.db.project.tinytwitter.entity.search.BlogSearchRequest;
import org.university.db.project.tinytwitter.entity.search.BlogSearchResponse;
import org.university.db.project.tinytwitter.entity.web.Response;
import org.university.db.project.tinytwitter.service.BlogService;

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
//        BlogSearchResponse response = new BlogSearchResponse();
        try {
            return Response.ok(blogService.searchBlog(request));
//            response.setResult(blogService.searchBlog(request));
//            response.setStatus(0);
        } catch (Exception e) {
            return Response.error(e.toString());
//            response.setStatus(1);
        }
//        return response;
    }

    @RequestMapping(value = "/add")
    public void addBlogs(@RequestBody Blog blog) {
        blogService.add(blog);
    }

    @RequestMapping(value = "/update")
    public void updateBlogs(@RequestBody Blog blog) {
        blogService.update(blog);
    }

    @RequestMapping(value = "/delete")
    public void deleteBlogs(@RequestBody List<Integer> blogs) {
        blogService.deleteBlogs(blogs);
    }
}
