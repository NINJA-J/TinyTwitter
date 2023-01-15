package org.university.db.project.tinytwitter.controller;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.university.db.project.tinytwitter.dao.StatisticMapper;
import org.university.db.project.tinytwitter.entity.BlogStatistics;
import org.university.db.project.tinytwitter.entity.UserStatistic;
import org.university.db.project.tinytwitter.entity.web.Response;

import java.util.List;

@RestController
@RequestMapping("statistics")
public class StatisticController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticController.class);

    private final StatisticMapper statisticMapper;

    @Autowired
    public StatisticController(StatisticMapper statisticMapper) {
        this.statisticMapper = statisticMapper;
    }

    @RequestMapping(value = "/user")
    public Response<List<UserStatistic>> showUserStatistics(
            @RequestBody(required = false) StatisticsRequest request
    ) {
        if (request == null) request = StatisticsRequest.INSTANT;
        try {
            return Response.ok(statisticMapper.getUserStatistics(
                    request.getOrderColId(),
                    request.getDesc(),
                    request.getLimit(20)
            ));
        } catch (Exception e) {
            LOGGER.error("Query user statistics failed", e);
            return Response.error(e.toString());
        }
    }

    @RequestMapping("/blog")
    public Response<List<BlogStatistics>> showBlogStatistics(
            @RequestBody(required = false) StatisticsRequest request) {
        if (request == null) request = StatisticsRequest.INSTANT;
        try {
            return Response.ok(statisticMapper.getBlogStatistics(
                    request.getOrderColId(),
                    request.getDesc(),
                    request.getLimit(50)
            ));
        } catch (Exception e) {
            LOGGER.error("Query blog statistics failed", e);
            return Response.error(e.toString());
        }
    }

    @Data
    public static class StatisticsRequest {
        public static final StatisticsRequest INSTANT = new StatisticsRequest();

        private Integer orderColId;

        private Boolean desc;

        private Integer limit;

        public int getOrderColId() {
            return orderColId == null ? 0 : orderColId;
        }

        public boolean getDesc() {
            return desc == null || desc;
        }

        public int getLimit(int defaultLimit) {
            return limit == null ? defaultLimit : limit;
        }

    }
}
