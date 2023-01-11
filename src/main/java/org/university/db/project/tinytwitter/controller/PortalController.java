package org.university.db.project.tinytwitter.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.university.db.project.tinytwitter.controller.base.AbstractMenuController;
import org.university.db.project.tinytwitter.entity.User;
import org.university.db.project.tinytwitter.service.UserService;

@Controller
@RequestMapping("/auth")
public class PortalController extends AbstractMenuController {

    private final UserService userService;

    @Autowired
    protected PortalController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public @ResponseBody
    LoginResponse login(@RequestBody LoginRequest request) {
        LoginResponse response = new LoginResponse();
        try {
            User user = userService.login(request.getUsername(), request.getPassword());
            if (user == null) {
                response.setStatus(ResponseStatus.ERROR.code);
                response.setMessage("Invalid username or Password");
            } else {
                response.setStatus(ResponseStatus.SUCCESS.code);
                response.setUser(user);
            }
        } catch (Exception e) {
            response.setStatus(ResponseStatus.ERROR.code);
            response.setMessage("Internal Server Error, try again");
        }
        return response;
    }

    @RequestMapping("/register")
    public @ResponseBody
    LoginResponse register(@RequestBody User user) {
        LoginResponse response = new LoginResponse();
        try {
            userService.add(user);
            response.setStatus(ResponseStatus.SUCCESS.code);
            response.setUser(user);
        } catch (Exception e) {
            response.setStatus(ResponseStatus.ERROR.code);
            response.setMessage("Internal Server Error, try again");
        }
        return response;
    }

    @Data
    private static class LoginRequest {
        private String username;

        private String password;
    }

    @Data
    private static class LoginResponse {
        private int status;

        private String message;

        private User user;
    }
}
