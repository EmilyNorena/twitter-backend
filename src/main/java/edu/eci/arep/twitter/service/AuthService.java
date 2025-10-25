package edu.eci.arep.twitter.service;

import edu.eci.arep.twitter.model.User;
import edu.eci.arep.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository repo;
    
    public Optional<User> findByEmail(String email) {
        return repo.findByEmail(email);
    }

    public User createUserIfNotExists(String email, String name) {
        return repo.findByEmail(email).orElseGet(() -> {
            User user = new User();
            user.setEmail(email);
            user.setName(name);
            return repo.save(user);
        });
    }
}
