package com.zavalin.service;

import com.zavalin.domain.Instructor;
import com.zavalin.web.model.InstructorRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class InstructorServiceImpl implements InstructorService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Instructor save(InstructorRequest instructorRequest) {
        Instructor instructor = new Instructor();
        instructor.setFirstName(instructorRequest.getFirstName());
        instructor.setLastName(instructorRequest.getLastName());
        instructor.setUniversity(instructorRequest.getUniversity());

        entityManager.persist(instructor);
        return instructor;
    }

    @Override
    public Instructor getById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    public void delete(Instructor instructor) {
        entityManager.remove(instructor);
    }

    @Override
    public Instructor update(int id, InstructorRequest instructorRequest) {
        Instructor instructor = getById(id);
        instructor.setFirstName(instructorRequest.getFirstName() != null ?
                instructorRequest.getFirstName() : instructor.getFirstName());
        instructor.setLastName(instructorRequest.getLastName() != null ?
                instructorRequest.getLastName() : instructor.getLastName());
        instructor.setUniversity(instructorRequest.getUniversity() != null ?
                instructorRequest.getUniversity() : instructor.getUniversity());

        entityManager.persist(instructor);
        return instructor;
    }
}
