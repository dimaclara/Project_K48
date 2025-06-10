package teck.marie.blogmanager.service;

import org.springframework.stereotype.Service;
import teck.marie.blogmanager.model.Comment;
import teck.marie.blogmanager.model.Post;
import teck.marie.blogmanager.repository.CommentRepository;
import teck.marie.blogmanager.repository.PostRepository;

@Service
public class CommentImplService implements CommentService{

    //injection of Beans

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    public CommentImplService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }


    // implementation of method to add comment in Post


    @Override
    public Comment addCommentToPost(Long postId, Comment comment) {
       Post post = postRepository.findById(postId)
               .orElseThrow(() -> new RuntimeException("Post not found"));
        comment.setPost(post);
        return commentRepository.save(comment);
    }



    // delete Comment from Post


    @Override
    public void DeleteCommentFromPost(Long postId, Long commentId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        comment.setPost(post);
        commentRepository.delete(comment);

    }
}
