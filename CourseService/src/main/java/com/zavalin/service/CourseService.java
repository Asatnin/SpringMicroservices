package com.zavalin.service;

import com.zavalin.domain.Course;
import com.zavalin.web.model.CourseRequest;
import com.zavalin.web.model.StatisticsRequest;

import java.util.List;

public interface CourseService {
    Course save(CourseRequest courseRequest);
    Course save(Course course);
    List<Course> getAllCourses(int page, int size);
    Course getById(int id);
    Course update(int courseId, CourseRequest courseRequest);
    int addStatisticsToCourse(int courseId, StatisticsRequest statisticsRequest);
    void deleteStatisticsFromCourse(int courseId, int statisticsId);
//    int addInstructorToCourse(int courseId, InstructorRequest instructorRequest);
//    void deleteInstructorFromCourse(int courseId, int instructorId);
//    List<Statistics> allStatistics(int courseId);
//    List<Statistics> allStatistics(int courseId, int page, int size);
//    List<Instructor> allInstructors(int courseId);
//    List<Instructor> allInstructors(int courseId, int page, int size);
}
