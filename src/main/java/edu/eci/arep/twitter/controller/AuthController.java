package edu.eci.arep.twitter.controller;

import edu.eci.arep.twitter.model.User;
import edu.eci.arep.twitter.repository.UserRepository;
import edu.eci.arep.twitter.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired private UserRepository userRepo;

    @PostMapping
    public Map<String, Object> login(@RequestBody Map<String, String> data) {
        String username = data.get("username");
        String password = data.get("password");

        if (authService.validateCredentials(username, password)) {
            User u = userRepo.findByUsername(username).orElseThrow();
            return Map.of(
                    "status", "ok",
                    "userId", u.getId(),
                    "message", "Usuario autenticado correctamente"
            );
        } else {
            return Map.of("status", "error", "message", "Credenciales inválidas");
        }
    }
}