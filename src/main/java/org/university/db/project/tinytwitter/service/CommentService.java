package org.university.db.project.tinytwitter.service;

import org.springframework.stereotype.Service;
import org.university.db.project.tinytwitter.dao.CommentMapper;
import org.university.db.project.tinytwitter.entity.Comment;
import org.university.db.project.tinytwitter.entity.search.CommentSearchRequest;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentService {

    @Resource
    private CommentMapper commentMapper;

    public boolean add(Comment service) {
        return commentMapper.insert(service) == 1;
    }

    public boolean update(Comment service) {
        return commentMapper.updateByPrimaryKey(service) == 1;
    }

    public boolean deleteById(Integer id) {
        return commentMapper.deleteByPrimaryKey(id) == 1;
    }

    public List<Comment> searchComments(CommentSearchRequest request) {
        Integer page = request.getPage();
        if (page != null && page < 0) {
            page = 0;
        }
        Integer pageSize = request.getPageSize();
        if (pageSize != null) {
            if (pageSize < 10) {
                pageSize = 10;
            } else if (pageSize > 50) {
                pageSize = 50;
            }
        }
        return commentMapper.getCommentByFilter(request.getBlogId(),
                wrapLike(request.getUser()),
                wrapLike(request.getContent()),
                page == null ? page : page * pageSize, pageSize);
    }

    private String wrapLike(String str) {
        if (str == null || str.trim().length() == 0) {
            return null;
        }
        return "%" + str + "%";
    }
}
