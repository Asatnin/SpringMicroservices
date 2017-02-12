package com.zavalin.service;

import com.zavalin.domain.Course;
import com.zavalin.domain.Statistics;
import com.zavalin.web.model.CourseRequest;
import com.zavalin.web.model.StatisticsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class CourseServiceImpl implements CourseService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private StatisticsService statisticsService;

    @Override
    public Course save(CourseRequest courseRequest) {
        Course course = new Course();
        course.setTitle(courseRequest.getTitle());

        entityManager.persist(course);
        return course;
    }

    @Override
    public Course save(Course course) {
        entityManager.persist(course);
        return course;
    }

    @Override
    public List<Course> getAllCourses(int page, int size) {
        TypedQuery<Course> query = entityManager.createQuery("from Course", Course.class);
        query.setFirstResult(page * size);
        query.setMaxResults(size);
        return query.getResultList();
    }

    @Override
    public Course getById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    public Course update(int courseId, CourseRequest courseRequest) {
        Course course = getById(courseId);
        course.setTitle(courseRequest.getTitle() != null ? courseRequest.getTitle() : course.getTitle());

        entityManager.persist(course);
        return course;
    }

    @Override
    public int addStatisticsToCourse(int courseId, StatisticsRequest statisticsRequest) {
        Course course = getById(courseId);
        Statistics statistics = statisticsService.save(statisticsRequest);
        course.getStatisticsList().add(statistics);

        entityManager.persist(course);
        return statistics.getId();
    }

    @Override
    public void deleteStatisticsFromCourse(int courseId, int statisticsId) {
        Course course = getById(courseId);
        Statistics statistics = statisticsService.getById(statisticsId);
        statisticsService.deleteById(statisticsId);
        course.getStatisticsList().remove(statistics);

        entityManager.persist(course);
    }

/*    @Override
    public int addInstructorToCourse(int courseId, InstructorRequest instructorRequest) {
        Course course = getById(courseId);
        Instructor instructor = instructorService.save(instructorRequest);
        course.getInstructors().add(instructor);
        sessionFactory.getCurrentSession().save(course);
        return instructor.getId();
    }

    @Override
    public void deleteInstructorFromCourse(int courseId, int instructorId) {
        Course course = getById(courseId);
        Instructor instructor = instructorService.getById(instructorId);
        instructorService.delete(instructor);
        course.getInstructors().remove(instructor);
        sessionFactory.getCurrentSession().save(course);
    }

    @Override
    public List<Statistics> allStatistics(int courseId) {
        return sessionFactory.getCurrentSession().get(Course.class, courseId).getStatisticsList();
    }

    @Override
    public List<Statistics> allStatistics(int courseId, int page, int size) {
        List<Statistics> list = allStatistics(courseId);
        if (page * size >= list.size()) {
            list = new ArrayList<>();
        } else {
            list = list.subList(page * size, Math.min(page * size + size, list.size()));
        }
        return list;
    }

    @Override
    public List<Instructor> allInstructors(int courseId) {
        return sessionFactory.getCurrentSession().get(Course.class, courseId).getInstructors();
    }

    @Override
    public List<Instructor> allInstructors(int courseId, int page, int size) {
        List<Instructor> list = allInstructors(courseId);
        if (page * size >= list.size()) {
            list = new ArrayList<>();
        } else {
            list = list.subList(page * size, Math.min(page * size + size, list.size()));
        }
        return list;
    }*/
}
