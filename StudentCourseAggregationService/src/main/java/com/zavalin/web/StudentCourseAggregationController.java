package com.zavalin.web;

import com.zavalin.web.model.CourseResponse;
import com.zavalin.web.model.StudentResponse;
import com.zavalin.web.model.StudentResponseWithCourseIds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentCourseAggregationController {
    @Autowired
    private RestTemplate restTemplate;

    private static final String studentsUrl = "http://localhost:8081/students";
    private static final String coursesUrl = "http://localhost:8082/courses/{id}";

    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public List<StudentResponse> getStudentsList(@RequestParam("page") int page,
                                                 @RequestParam("size") int size,
                                                 HttpServletResponse response) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(studentsUrl)
                .queryParam("page", page)
                .queryParam("size", size);
        URI studentsUri = builder.build().toUri();

        StudentResponseWithCourseIds[] students;
        try {
            students = restTemplate.getForObject(studentsUri, StudentResponseWithCourseIds[].class);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            return null;
        }

            List<StudentResponse> resultResponse = new ArrayList<>();
            for (StudentResponseWithCourseIds student : students) {
                StudentResponse studentToAdd = new StudentResponse(student);
                for (int i : student.getCoursesIds()) {
                    CourseResponse course;
                    try {
                        course = restTemplate.getForObject(coursesUrl, CourseResponse.class, i);
                    } catch (Exception e) {
                        response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                        return null;
                    }
                    studentToAdd.getCourses().add(course);
                }
                resultResponse.add(studentToAdd);
            }
        return resultResponse;
    }
}
