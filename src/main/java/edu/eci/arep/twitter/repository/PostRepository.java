package edu.eci.arep.twitter.repository;

import edu.eci.arep.twitter.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
