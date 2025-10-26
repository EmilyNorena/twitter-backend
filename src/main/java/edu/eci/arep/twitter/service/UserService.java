package edu.eci.arep.twitter.service;

import edu.eci.arep.twitter.model.User;
import edu.eci.arep.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public User findByEmail(String email) {
        return repo.findByEmail(email).orElse(null);
    }

    public Boolean existsByEmail(String email) {
        return repo.existsByEmail(email);
    }

    public User createUserIfNotExists(String email, String name) {
        return repo.findByEmail(email).map(user -> {
        if (!name.equals(user.getName())) {
            user.setName(name);
            return repo.save(user);
        }
        return user;
    }).orElseGet(() -> {
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        return repo.save(user);
    });
    }
}
