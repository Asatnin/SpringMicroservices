package com.zavalin.web;

import com.zavalin.service.AccessTokenService;
import com.zavalin.service.UserService;
import com.zavalin.web.model.UserRequest;
import com.zavalin.web.model.UserResponse;
import com.zavalin.web.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserRegistrationController {
    @Autowired
    private UserService userService;

    @Autowired
    private AccessTokenService accessTokenService;

    @RequestMapping(path = "/registration", method = RequestMethod.GET)
    public ModelAndView getUserRegistrationPage() {
        return new ModelAndView("registration", "userRequest", new UserRequest());
    }

    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    public ModelAndView registerUser(@ModelAttribute("userRequest") UserRequest userRequest) {
        userService.save(userRequest);
        return new ModelAndView("registration", "userRequest", new UserRequest());
    }

    @RequestMapping(path = "/me", method = RequestMethod.GET)
    @ResponseBody
    public UserResponse me(HttpServletRequest request) {
        String token = AuthUtils.extractToken(request.getHeader("Authorization"));
        return new UserResponse(accessTokenService.getAccessTokenInfo(token).getUser());
    }
}
