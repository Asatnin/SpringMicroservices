package com.zavalin.web;

import com.zavalin.domain.Student;
import com.zavalin.service.StudentService;
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
@RequestMapping("/students")
public class StudentRestController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private RestTemplate restTemplate;

    private static final String createCourseUrl = "http://localhost:8082/courses";
    private static final String updateCourseUrl = "http://localhost:8082/courses/{courseId}";

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<StudentResponseWithCourseIds> getStudentList(@RequestParam("page") int page,
                                                @RequestParam("size") int size) {
        return studentService.getAllStudents(page, size)
                .stream()
                .map(StudentResponseWithCourseIds::new)
                .collect(Collectors.toList());
    }

    @RequestMapping(path = "/{studentId}", method = RequestMethod.GET)
    @ResponseBody
    public StudentResponse getStudent(@PathVariable int studentId) {
        return new StudentResponse(studentService.getById(studentId));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public void createStudent(@RequestBody StudentRequest studentRequest, HttpServletResponse response) {
        Student student = studentService.save(studentRequest);
        response.addHeader(HttpHeaders.LOCATION, "/students/" + student.getId());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/{studentId}", method = RequestMethod.DELETE)
    public void deleteStudent(@PathVariable int studentId) {
        studentService.delete(studentId);
    }

    @RequestMapping(path = "/{studentId}", method = RequestMethod.PATCH)
    @ResponseBody
    public StudentResponse updateStudent(@PathVariable int studentId,
                                         @RequestBody StudentRequest studentRequest) {
        return new StudentResponse(studentService.update(studentId, studentRequest));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/{studentId}/courses", method = RequestMethod.POST)
    public void addCourseToStudent(@PathVariable int studentId, @RequestBody CourseRequest courseRequest,
                                   HttpServletResponse response) {
        int courseId;
        try {
            courseId = restTemplate.postForObject(createCourseUrl, courseRequest, Integer.class);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            return;
        }
        Student student = studentService.getById(studentId);
        student.getCoursesIds().add(courseId);
        studentService.save(student);
        response.addHeader(HttpHeaders.LOCATION, "/courses/" + courseId);
    }

    @RequestMapping(path = "/{studentId}/courses/{courseId}", method = RequestMethod.PATCH)
    @ResponseBody
    public CourseResponse updateCourseInStudent(@PathVariable int studentId,
                                                @PathVariable int courseId,
                                                @RequestBody CourseRequest courseRequest,
                                                HttpServletResponse response) {
        HttpEntity<CourseRequest> entity = new HttpEntity<>(courseRequest);
        ResponseEntity<CourseResponse> responseEntity;
        try {
            responseEntity = restTemplate.exchange(updateCourseUrl, HttpMethod.PATCH,
                    entity, CourseResponse.class, courseId);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            return null;
        }
        return responseEntity.getBody();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/{studentId}/courses/{courseId}", method = RequestMethod.DELETE)
    public void deleteCourseFromStudent(@PathVariable int studentId,
                                                @PathVariable int courseId) {
        Student student = studentService.getById(studentId);
        student.getCoursesIds().remove(courseId);
        studentService.save(student);
    }
}
