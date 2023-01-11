package org.university.db.project.tinytwitter.entity.search;

import lombok.Data;
import org.university.db.project.tinytwitter.entity.Blog;

import java.util.List;

@Data
public class BlogSearchResponse {
    private int status;

    private List<Blog> result;
}
