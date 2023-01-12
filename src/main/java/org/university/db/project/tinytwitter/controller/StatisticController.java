package org.university.db.project.tinytwitter.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.university.db.project.tinytwitter.dao.StatisticMapper;
import org.university.db.project.tinytwitter.entity.BlogStatistics;
import org.university.db.project.tinytwitter.entity.UserStatistic;
import org.university.db.project.tinytwitter.entity.web.Response;

import java.util.List;

@Controller
@RequestMapping("statistics")
public class StatisticController {

    private final StatisticMapper statisticMapper;

    @Autowired
    public StatisticController(StatisticMapper statisticMapper) {
        this.statisticMapper = statisticMapper;
    }

    @RequestMapping(value = "/user")
    public @ResponseBody
    Response<List<UserStatistic>> showUserStatistics() {
        try {
            return Response.ok(statisticMapper.getUserStatistics());
        } catch (Exception e) {
            return Response.error(e.toString());
        }
    }

    @RequestMapping("/blog")
    public @ResponseBody
    Response<List<BlogStatistics>> showBlogStatistics() {
        try {
            return Response.ok(statisticMapper.getBlogStatistics());
        } catch (Exception e) {
            return Response.error(e.toString());
        }
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
