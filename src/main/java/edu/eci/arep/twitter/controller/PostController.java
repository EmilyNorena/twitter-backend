package edu.eci.arep.twitter.controller;

import edu.eci.arep.twitter.dto.PostDTO;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @PostMapping
    public PostDTO createPost(@RequestBody Map<String, String> body,
                              @AuthenticationPrincipal Jwt jwt) {
        
        System.out.println(jwt.getClaims());
        String email = jwt.getClaim("email");
        String name = jwt.getClaim("name");
        System.out.println("JWT email: " + email + ", name: " + name);
        User author = userService.createUserIfNotExists(email, name);

        Post post = postService.createPost(author, body.get("body"), null);
        return new PostDTO(post);
    }

    @GetMapping
    public List<PostDTO> getStream() {

        return postService.getAllPosts()
                          .stream()
                          .map(PostDTO::new)
                          .collect(Collectors.toList());
    }
}
