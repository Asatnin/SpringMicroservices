package com.zavalin.service;

import com.zavalin.domain.Statistics;
import com.zavalin.web.model.StatisticsRequest;

public interface StatisticsService {
    Statistics getById(int id);
    Statistics save(StatisticsRequest statisticsRequest);
    void deleteById(int id);
    Statistics updateStatistics(int id, StatisticsRequest statisticsRequest);
}
