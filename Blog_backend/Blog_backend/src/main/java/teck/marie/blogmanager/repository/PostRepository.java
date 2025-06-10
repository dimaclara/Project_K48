package teck.marie.blogmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teck.marie.blogmanager.model.Post;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByTitle(String title);
}
