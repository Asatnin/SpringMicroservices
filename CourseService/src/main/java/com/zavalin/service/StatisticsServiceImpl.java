package com.zavalin.service;

import com.zavalin.domain.Statistics;
import com.zavalin.web.model.StatisticsRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class StatisticsServiceImpl implements StatisticsService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Statistics getById(int id) {
        return entityManager.find(Statistics.class, id);
    }

    @Override
    public Statistics save(StatisticsRequest statisticsRequest) {
        Statistics statistics = new Statistics();
        statistics.setYear(statisticsRequest.getYear());
        statistics.setAverageScore(statisticsRequest.getAverageScore());

        entityManager.persist(statistics);
        return statistics;
    }

    @Override
    public void deleteById(int id) {
        Statistics statistics = getById(id);
        entityManager.remove(statistics);
    }

    @Override
    public Statistics updateStatistics(int id, StatisticsRequest statisticsRequest) {
        Statistics statistics = getById(id);
        statistics.setAverageScore(statisticsRequest.getAverageScore());
        statistics.setYear(statisticsRequest.getYear());

        entityManager.persist(statistics);
        return statistics;
    }
}
