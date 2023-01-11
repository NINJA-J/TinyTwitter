package org.university.db.project.tinytwitter.entity.search;

import lombok.Data;

@Data
public class BlogSearchRequest {
    private Integer userId;

    private String user;

    private String blogTitle;

    private String blogContent;

    private String blogType;

    private Boolean isLike;

    private Boolean isCollect;

    private Integer page;

    private Integer pageSize;
}
