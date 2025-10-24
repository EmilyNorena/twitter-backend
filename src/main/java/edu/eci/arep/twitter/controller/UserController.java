package edu.eci.arep.twitter.controller;

import edu.eci.arep.twitter.model.User;
import edu.eci.arep.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired private UserService service;

    @PostMapping
    public User register(@RequestBody User user) {
        return service.register(user);
    }
}
