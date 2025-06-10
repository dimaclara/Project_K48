package teck.marie.blogmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teck.marie.blogmanager.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
