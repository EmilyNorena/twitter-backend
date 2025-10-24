package edu.eci.arep.twitter.service;

import edu.eci.arep.twitter.model.Post;
import edu.eci.arep.twitter.model.User;
import edu.eci.arep.twitter.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    @Autowired private PostRepository repo;

    public Post createPost(User author, String body) {
        if (body.length() > 140)
            throw new IllegalArgumentException("El post no puede superar los 140 caracteres");
        Post p = new Post();
        p.setAuthor(author);
        p.setBody(body);
        p.setDate(LocalDateTime.now());
        return repo.save(p);
    }

    public List<Post> getStream() {
        return repo.findAll();
    }
}