package com.zavalin.service;

import com.zavalin.domain.Instructor;
import com.zavalin.web.model.InstructorRequest;

public interface InstructorService {
    Instructor save(InstructorRequest instructorRequest);
    Instructor getById(int id);
    void delete(Instructor instructor);
    Instructor update(int id, InstructorRequest instructorRequest);
}
