package org.university.db.project.tinytwitter.entity;

import lombok.Data;

import java.util.Date;

@Data
public class BlogStatistics {
    private String title;

    private String author;

    private String lastUpdateDate;

    private int likes;

    private int collects;

    private int comments;
}
