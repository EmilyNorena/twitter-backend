package edu.eci.arep.twitter.controller;

import edu.eci.arep.twitter.model.User;
import edu.eci.arep.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/sync")
    public ResponseEntity<?> syncUser(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String name = payload.get("name");

        if (email == null || name == null) {
            return ResponseEntity.badRequest().body("Error: email y name son requeridos");
        }
        User user = service.createUserIfNotExists(email, name);
        return ResponseEntity.ok(user);
    }
}
