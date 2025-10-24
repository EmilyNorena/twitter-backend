package edu.eci.arep.twitter.service;

import edu.eci.arep.twitter.model.User;
import edu.eci.arep.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired private UserRepository repo;
    @Autowired private PasswordEncoder passwordEncoder;

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public User findByUsername(String email) {
        return repo.findByUsername(email).orElse(null);
    }
}
