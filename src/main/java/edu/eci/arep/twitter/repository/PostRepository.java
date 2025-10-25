package edu.eci.arep.twitter.repository;

import edu.eci.arep.twitter.model.Post;
import edu.eci.arep.twitter.model.Stream;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByStream(Stream stream);
}
