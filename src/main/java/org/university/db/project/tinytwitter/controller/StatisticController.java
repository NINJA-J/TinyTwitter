package org.university.db.project.tinytwitter.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.university.db.project.tinytwitter.controller.base.AbstractMenuController;
import org.university.db.project.tinytwitter.dao.StatisticMapper;
import org.university.db.project.tinytwitter.entity.BlogStatistics;
import org.university.db.project.tinytwitter.entity.UserStatistic;

import java.util.List;

@Controller
@RequestMapping("/statistics")
public class StatisticController extends AbstractMenuController {

    @Autowired
    private StatisticMapper statisticMapper;

    @RequestMapping("/user")
    public UserStatisticsResponse showUserStatistics() {
        UserStatisticsResponse response = new UserStatisticsResponse();
        try {
            response.setResult(statisticMapper.getUserStatistics());
            response.setStatus(ResponseStatus.SUCCESS.code);
        } catch (Exception e) {
            response.setStatus(ResponseStatus.ERROR.code);
        }
        return response;
    }

    @RequestMapping("/blog")
    public @ResponseBody
    BlogStatisticsResponse showBlogStatistics() {
        BlogStatisticsResponse response = new BlogStatisticsResponse();
        try {
            response.setResult(statisticMapper.getBlogStatistics());
            response.setStatus(ResponseStatus.SUCCESS.code);
        } catch (Exception e) {
            response.setStatus(ResponseStatus.ERROR.code);
        }
        return response;
    }

    @Data
    public static class UserStatisticsResponse {
        private int status;

        private List<UserStatistic> result;
    }

    @Data
    public static class BlogStatisticsResponse {
        private int status;

        private List<BlogStatistics> result;
    }
}
