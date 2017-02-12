package com.zavalin.web;

import com.zavalin.domain.Course;
import com.zavalin.service.CourseService;
import com.zavalin.service.StatisticsService;
import com.zavalin.web.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/courses")
public class CourseRestController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private RestTemplate restTemplate;

    private static final String createInstructorUrl = "http://localhost:8084/instructors";
    private static final String updateInstructorUrl = "http://localhost:8084/instructors/{instructorId}";

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Integer createCourse(@RequestBody CourseRequest courseRequest, HttpServletResponse response) {
        Course course = courseService.save(courseRequest);
        response.addHeader(HttpHeaders.LOCATION, "/courses/" + course.getId());
        return course.getId();
    }


    @RequestMapping(path = "/{courseId}", method = RequestMethod.GET)
    @ResponseBody
    public CourseResponse getCourse(@PathVariable int courseId) {
        return new CourseResponse(courseService.getById(courseId));
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<CourseResponseStInst> getAllCourses(@RequestParam("page") int page,
                                                        @RequestParam("size") int size) {
        return courseService.getAllCourses(page, size)
                .stream()
                .map(CourseResponseStInst::new)
                .collect(Collectors.toList());
    }

    @RequestMapping(path = "/{courseId}", method = RequestMethod.PATCH)
    @ResponseBody
    public CourseResponse updateCourse(@PathVariable int courseId,
                                         @RequestBody CourseRequest courseRequest) {
        return new CourseResponse(courseService.update(courseId, courseRequest));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/{courseId}/statistics", method = RequestMethod.POST)
    public void addStatisticsToCourse(@PathVariable int courseId,
                                      @RequestBody StatisticsRequest statisticsRequest,
                                      HttpServletResponse response) {
        response.addHeader(HttpHeaders.LOCATION,
                "/" + courseId + "/statistics/"
                        + courseService.addStatisticsToCourse(courseId, statisticsRequest));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/{courseId}/statistics/{statisticsId}", method = RequestMethod.DELETE)
    public void deleteStatisticsFromCourse(@PathVariable int courseId, @PathVariable int statisticsId) {
        courseService.deleteStatisticsFromCourse(courseId, statisticsId);
    }

    @RequestMapping(path = "/{courseId}/statistics/{statisticsId}", method = RequestMethod.PATCH)
    @ResponseBody
    public StatisticsResponse updateStatistics(@PathVariable int courseId, @PathVariable int statisticsId,
                                               @RequestBody StatisticsRequest statisticsRequest) {
        return new StatisticsResponse(statisticsService.updateStatistics(statisticsId, statisticsRequest));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/{courseId}/instructors", method = RequestMethod.POST)
    public void addInstructorToCourse(@PathVariable int courseId,
                                      @RequestBody InstructorRequest instructorRequest,
                                      HttpServletResponse response) {
        int instructorId;
        try {
            instructorId = restTemplate.postForObject(createInstructorUrl, instructorRequest, Integer.class);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            return;
        }
        Course course = courseService.getById(courseId);
        course.getInstructorsIds().add(instructorId);
        courseService.save(course);
        response.addHeader(HttpHeaders.LOCATION,
                "/" + courseId + "/instructors/" + instructorId);
    }

    @RequestMapping(path = "/{courseId}/instructors/{instructorsId}", method = RequestMethod.PATCH)
    @ResponseBody
    public InstructorResponse updateCourseInStudent(@PathVariable int courseId,
                                                @PathVariable int instructorId,
                                                @RequestBody InstructorRequest instructorRequest,
                                                    HttpServletResponse response) {
        HttpEntity<InstructorRequest> entity = new HttpEntity<>(instructorRequest);
        ResponseEntity<InstructorResponse> responseEntity;
        try {
            responseEntity = restTemplate.exchange(updateInstructorUrl, HttpMethod.PATCH, entity,
                    InstructorResponse.class, instructorId);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            return null;
        }
        return responseEntity.getBody();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/{courseId}/instructors/{instructorsId}", method = RequestMethod.DELETE)
    public void deleteCourseFromStudent(@PathVariable int courseId, @PathVariable int instructorsId) {
        Course course = courseService.getById(courseId);
        course.getInstructorsIds().remove(instructorsId);
        courseService.save(course);
    }

/*    @RequestMapping(path = "/{courseId}/statistics", method = RequestMethod.GET)
    @ResponseBody
    public List<StatisticsResponse> allStatistics(@PathVariable int courseId,
                                                  @RequestParam("page") int page,
                                                  @RequestParam("size") int size) {
        return courseService.allStatistics(courseId, page, size)
                .stream()
                .map(StatisticsResponse::new)
                .collect(Collectors.toList());
    }

    @RequestMapping(path = "/{courseId}/instructors", method = RequestMethod.GET)
    @ResponseBody
    public List<InstructorResponse> allInstructors(@PathVariable int courseId,
                                                   @RequestParam("page") int page,
                                                   @RequestParam("size") int size) {
        return courseService.allInstructors(courseId, page, size)
                .stream()
                .map(InstructorResponse::new)
                .collect(Collectors.toList());
    }*/
}
