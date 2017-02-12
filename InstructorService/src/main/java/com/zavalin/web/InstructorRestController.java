package com.zavalin.web;

import com.zavalin.domain.Instructor;
import com.zavalin.service.InstructorService;
import com.zavalin.web.model.InstructorRequest;
import com.zavalin.web.model.InstructorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/instructors")
public class InstructorRestController {
    @Autowired
    private InstructorService instructorService;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public int createInstructor(@RequestBody InstructorRequest instructorRequest,
                                HttpServletResponse response) {
        Instructor instructor = instructorService.save(instructorRequest);
        response.addHeader(HttpHeaders.LOCATION, "/instructors/" + instructor.getId());
        return instructor.getId();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public InstructorResponse getInstructor(@PathVariable int id) {
        return new InstructorResponse(instructorService.getById(id));
    }

    @RequestMapping(path = "/{instructorId}", method = RequestMethod.PATCH)
    @ResponseBody
    public InstructorResponse updateInstructor(@PathVariable int instructorId,
                                           @RequestBody InstructorRequest instructorRequest) {
        return new InstructorResponse(instructorService.update(instructorId, instructorRequest));
    }
}
