package org.university.db.project.tinytwitter.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.university.db.project.tinytwitter.entity.BlogStatistics;
import org.university.db.project.tinytwitter.entity.UserStatistic;

import java.util.List;

@Mapper
public interface StatisticMapper {
    @Select("<script>" +
            "select title, user.name author, DATE_FORMAT(update_date,'%Y-%m-%d') update_date," +
            "       ifnull(like_stat.likes, 0) likes, ifnull(collect_stat.collects, 0) collects," +
            "       ifnull(cmts, 0) comments from blog" +
            "    left join user on blog.author = user.user_id" +
            "    left join (select blog_id, count(user_id) likes from `like` group by blog_id) like_stat " +
            "        on blog.blog_id = like_stat.blog_id" +
            "    left join (select blog_id, count(user_id) collects from collection group by blog_id) collect_stat " +
            "        on blog.blog_id = collect_stat.blog_id" +
            "    left join (select blog_id, count(comment_id) cmts from comment group by blog_id) comment_cnt " +
            "        on blog.blog_id = comment_cnt.blog_id" +
            "    order by" +
            "    <if test='orderColId == 0'> title </if>" +
            "    <if test='orderColId == 1'> author </if>" +
            "    <if test='orderColId == 2'> update_date </if>" +
            "    <if test='orderColId == 3'> likes </if>" +
            "    <if test='orderColId == 4'> collects </if>" +
            "    <if test='orderColId == 5'> comments </if>" +
            "    <if test='desc'> desc </if>" +
            "    <if test='!desc'> asc </if>" +
            "    limit #{limit}" +
            "</script>")
    @Results(value = {
            @Result(column = "title", property = "title"),
            @Result(column = "author", property = "author"),
            @Result(column = "update_date", property = "lastUpdateDate"),
            @Result(column = "likes", property = "likes"),
            @Result(column = "collects", property = "collects"),
            @Result(column = "comments", property = "comments")
    })
    List<BlogStatistics> getBlogStatistics(int orderColId, boolean desc, int limit);

    @Select("<script>" +
            "select name, ifnull(blogs, 0) blogs, ifnull(avg_blog_len, 0) avg_blog_len," +
            "       ifnull(comments, 0) comments, ifnull(avg_comment_len, 0) avg_cmt_len from user" +
            "    left join (select author, count(blog_id) blogs, avg(length(content)) avg_blog_len " +
            "            from blog group by author) b " +
            "        on user.user_id = b.author" +
            "    left join (select author, count(comment_id) comments, avg(length(content)) avg_comment_len " +
            "            from comment group by author) c " +
            "        on user_id = c.author" +
            "    order by" +
            "    <if test='orderColId == 0'> name </if>" +
            "    <if test='orderColId == 1'> blogs </if>" +
            "    <if test='orderColId == 2'> avg_blog_len </if>" +
            "    <if test='orderColId == 3'> comments </if>" +
            "    <if test='orderColId == 4'> avg_cmt_len </if>" +
            "    <if test='desc'> desc </if>" +
            "    <if test='!desc'> asc </if>" +
            "    limit #{limit}" +
            "</script>")
    @Results(value = {
            @Result(column = "name", property = "name"),
            @Result(column = "blogs", property = "blogs"),
            @Result(column = "avg_blog_len", property = "avgBlogLen"),
            @Result(column = "comments", property = "comments"),
            @Result(column = "avg_cmt_len", property = "avgCommentLen")
    })
    List<UserStatistic> getUserStatistics(int orderColId, boolean desc, int limit);
}
