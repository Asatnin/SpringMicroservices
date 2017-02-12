package com.zavalin.service;

import com.zavalin.domain.Student;
import com.zavalin.web.model.StudentRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class StudentServiceImpl implements StudentService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Student> getAllStudents(int page, int size) {
        TypedQuery<Student> query = entityManager.createQuery("from Student", Student.class);
        query.setFirstResult(page * size);
        query.setMaxResults(size);
        return query.getResultList();
    }

    @Override
    public Student getById(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public Student save(StudentRequest studentRequest) {
        Student student = new Student();
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setDayOfBirth(studentRequest.getDayOfBirth());

        entityManager.persist(student);
        return student;
    }

    @Override
    public Student save(Student student) {
        entityManager.persist(student);
        return student;
    }

    @Override
    public void delete(int id) {
        Student student = getById(id);
        entityManager.remove(student);
    }

    @Override
    public Student update(int id, StudentRequest studentRequest) {
        Student student = getById(id);
        student.setFirstName(studentRequest.getFirstName() != null ?
                studentRequest.getFirstName() : student.getFirstName());
        student.setLastName(studentRequest.getLastName() != null ?
                studentRequest.getLastName() : student.getLastName());
        student.setDayOfBirth(studentRequest.getDayOfBirth() != null ?
                studentRequest.getDayOfBirth() : student.getDayOfBirth());

        entityManager.persist(student);
        return student;
    }
}
