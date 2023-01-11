package org.university.db.project.tinytwitter.entity.search;

import lombok.Data;

@Data
public class CommentSearchRequest {
    private Integer blogId;

    private String user;

    private String content;

    private Integer page;

    private Integer pageSize;
}
