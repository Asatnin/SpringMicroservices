package com.zavalin.web;

import com.zavalin.web.model.CourseResponse;
import com.zavalin.web.model.CourseResponseStInst;
import com.zavalin.web.model.InstructorResponse;
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
public class CourseInstructorAggergationRestController {
    @Autowired
    private RestTemplate restTemplate;

    private static final String coursesUrl = "http://localhost:8082/courses";
    private static final String instructorsUrl = "http://localhost:8084/instructors/{id}";

    @RequestMapping(path = "/courses", method = RequestMethod.GET)
    @ResponseBody
    public List<CourseResponse> getStudentsList(@RequestParam("page") int page,
                                                @RequestParam("size") int size,
                                                HttpServletResponse response) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(coursesUrl)
                .queryParam("page", page)
                .queryParam("size", size);
        URI coursesUri = builder.build().toUri();

        CourseResponseStInst[] courses;
        try {
            courses = restTemplate.getForObject(coursesUri, CourseResponseStInst[].class);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            return null;
        }

            List<CourseResponse> resultResponse = new ArrayList<>();
            for (CourseResponseStInst course : courses) {
                CourseResponse courseToAdd = new CourseResponse(course);
                for (int i : course.getInstructors()) {
                    InstructorResponse instructor;
                    try {
                        instructor = restTemplate.getForObject(instructorsUrl, InstructorResponse.class, i);
                    } catch (Exception e) {
                        response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
                        return null;
                    }
                    courseToAdd.getInstructors().add(instructor);
                }
                resultResponse.add(courseToAdd);
            }

        return resultResponse;
    }
}
