package org.university.db.project.tinytwitter.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Blog implements Serializable {

    private Integer blogId;

    private String title;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date updateDate;

    private Integer author;

    private String content;

    private User user;

    private List<Comment> comments;

    private int likes;

    private int collects;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", blogId=").append(blogId);
        sb.append(", title=").append(title);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", author=").append(author);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}