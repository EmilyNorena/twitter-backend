package edu.eci.arep.twitter.controller;

import edu.eci.arep.twitter.model.Post;
import edu.eci.arep.twitter.model.User;
import edu.eci.arep.twitter.repository.UserRepository;
import edu.eci.arep.twitter.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired private PostService postService;
    @Autowired private UserRepository usuarioRepo;

    @PostMapping
    public Post createPost(@RequestBody Map<String, String> body, @AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getClaim("username"); //Extrae el username del token de Cognito
        User author = usuarioRepo.findByUsername(username).orElseThrow();
        return postService.createPost(author, body.get("body"));
    }

    @GetMapping
    public List<Post> getStream() {
        return postService.getStream();
    }
}
