package com.zavalin.service;

import com.zavalin.domain.Student;
import com.zavalin.web.model.CourseRequest;
import com.zavalin.web.model.StudentRequest;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents(int page, int size);
    Student getById(int id);
    Student save(StudentRequest studentRequest);
    Student save(Student student);
    void delete(int id);
    Student update(int id, StudentRequest studentRequest);
}
