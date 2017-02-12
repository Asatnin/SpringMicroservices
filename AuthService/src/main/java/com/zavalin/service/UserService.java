package com.zavalin.service;

import com.zavalin.domain.User;
import com.zavalin.web.model.UserRequest;

public interface UserService {
    User save(UserRequest userRequest);
    User get(int id);
    User checkLogin(String login, String password);
}
