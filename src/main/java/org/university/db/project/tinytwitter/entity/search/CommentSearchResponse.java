package org.university.db.project.tinytwitter.entity.search;

import lombok.Data;
import org.university.db.project.tinytwitter.entity.Comment;

import java.util.List;

@Data
public class CommentSearchResponse {
    private int status;

    private String message;

    private List<Comment> results;
}
