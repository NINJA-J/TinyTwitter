package org.university.db.project.tinytwitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.university.db.project.tinytwitter.dao.BlogMapper;
import org.university.db.project.tinytwitter.entity.Blog;
import org.university.db.project.tinytwitter.entity.User;
import org.university.db.project.tinytwitter.entity.search.BlogSearchRequest;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Service
public class BlogService {

    private final BlogMapper blogMapper;

    @Autowired
    public BlogService(BlogMapper blogMapper) {
        this.blogMapper = blogMapper;
    }

    public boolean add(Blog blog) {
        blogMapper.insert(blog);
        return true;
    }

    public boolean update(Blog blog) {
        return blogMapper.updateByPrimaryKey(blog) == 1;
    }

    public boolean deleteBlogs(List<Integer> blogIds) {
        if (CollectionUtils.isEmpty(blogIds)) {
            return true;
        }
        blogMapper.deleteByBlogIds(blogIds);
        return true;
    }

    public boolean isLike(User user, Blog blog) {
        return blogMapper.isLike(user, blog);
    }

    public boolean isCollect(User user, Blog blog) {
        return blogMapper.isCollect(user, blog);
    }

    public void like(User user, Blog blog, boolean isLike) {
        if (isLike) {
            blogMapper.addLike(user.getUserId(), blog.getBlogId());
        } else {
            blogMapper.deleteLike(user.getUserId(), blog.getBlogId());
        }
    }

    public boolean collect(User user, Blog blog, boolean doCollect) {
        if (doCollect) {
            blogMapper.addCollect(user.getUserId(), blog.getBlogId());
        } else {
            blogMapper.deleteCollect(user.getUserId(), blog.getBlogId());
        }
        return true;
    }

    public List<Blog> searchBlog(BlogSearchRequest request) {
        if (request == null) {
            request = new BlogSearchRequest();
        }
        Integer page = request.getPage();
        if (page == null || page < 0) {
            page = 0;
        }
        Integer pageSize = request.getPageSize();
        if (pageSize == null || pageSize < 10) {
            pageSize = 10;
        } else if (pageSize > 50) {
            pageSize = 50;
        }
        return blogMapper.selectByFilter(
                wrapLike(request.getUser()),
                wrapLike(request.getBlogTitle()),
                wrapLike(request.getBlogContent()),
                request.getIsLike(),
                request.getIsCollect(),
                request.getUserId(),
                page,
                pageSize);
    }

    private String wrapLike(String str) {
        if (str == null || str.trim().length() == 0) {
            return null;
        }
        return "%" + str + "%";
    }
}
