package teck.marie.blogmanager.service;

import org.springframework.stereotype.Service;
import teck.marie.blogmanager.model.Comment;

@Service
public interface CommentService {
    Comment addCommentToPost(Long postId, Comment comment);

    void DeleteCommentFromPost(Long postId, Long commentId);

}
