package edu.eci.arep.twitter.service;

import edu.eci.arep.twitter.model.User;
import edu.eci.arep.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository repo;
    @Autowired private PasswordEncoder encoder;

    public boolean validateCredentials(String username, String password) {
        User user = repo.findByUsername(username).orElse(null);
        return user != null && encoder.matches(password, user.getPassword());
    }
}
