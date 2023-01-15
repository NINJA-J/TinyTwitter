package org.university.db.project.tinytwitter.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.university.db.project.tinytwitter.entity.User;
import org.university.db.project.tinytwitter.entity.web.Response;
import org.university.db.project.tinytwitter.service.UserService;

@RestController
@RequestMapping("/")
public class PortalController {

    private final UserService userService;

    @Autowired
    protected PortalController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index.html");
    }

    @RequestMapping("/auth/login")
    public Response<User> login(@RequestBody LoginRequest request) {
        try {
            User user = userService.login(request.getUsername(), request.getPassword());
            if (user == null) {
                return Response.error("Invalid Username or Password");
            } else {
                return Response.ok(user);
            }
        } catch (Exception e) {
            return Response.error("Internal Server Error, " + e);
        }
    }

    @RequestMapping("/auth/register")
    public Response<User> register(@RequestBody User user) {
        try {
            userService.add(user);
            return Response.ok(user);
        } catch (Exception e) {
            return Response.error("Internal Server Error, " + e);
        }
    }

    @Data
    private static class LoginRequest {
        private String username;

        private String password;
    }
}
