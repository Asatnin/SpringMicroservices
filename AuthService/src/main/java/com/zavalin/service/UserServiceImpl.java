package com.zavalin.service;

import com.zavalin.domain.User;
import com.zavalin.web.model.UserRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
@Transactional
public class UserServiceImpl implements UserService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User save(UserRequest userRequest) {
        User user = new User();
        user.setLogin(userRequest.getLogin());
        user.setPassword(userRequest.getPassword());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());

        entityManager.persist(user);
        return user;
    }

    @Override
    public User get(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User checkLogin(String login, String password) {
        TypedQuery<User> query = entityManager
                .createQuery("from User where login = :login and password = :password", User.class);
        query.setParameter("login", login);
        query.setParameter("password", password);

        return query.getResultList().size() == 0 ? null : query.getResultList().get(0);
    }
}
