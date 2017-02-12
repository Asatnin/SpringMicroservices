package com.zavalin.web.model;

import com.zavalin.domain.User;

public class UserResponse {
    private String firstName;
    private String lastName;

    public UserResponse(User user) {
        firstName = user.getFirstName();
        lastName = user.getLastName();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
