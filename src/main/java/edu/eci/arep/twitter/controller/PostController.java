package edu.eci.arep.twitter.controller;

import edu.eci.arep.twitter.model.Post;
import edu.eci.arep.twitter.model.User;
import edu.eci.arep.twitter.service.PostService;
import edu.eci.arep.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @PostMapping
    public Post createPost(@RequestBody Map<String, String> body,
                           @AuthenticationPrincipal Jwt jwt) {

        String email = jwt.getClaim("email");  
        String name = jwt.getClaim("name");   
        User author = userService.createUserIfNotExists(email, name);

        return postService.createPost(author, body.get("body"), null); 
    }

    @GetMapping
    public List<Post> getStream() {
        return postService.getAllPosts();
    }
}
