package teck.marie.blogmanager.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import teck.marie.blogmanager.exception.PostAlreadyExistsException;
import teck.marie.blogmanager.model.Post;
import teck.marie.blogmanager.repository.CommentRepository;
import teck.marie.blogmanager.repository.PostRepository;

import java.rmi.AlreadyBoundException;
import java.util.List;
import java.util.Optional;

@Service

public class PostImplService implements PostService{

    private  final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public PostImplService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Post creationOfpost(Post post) {

        Optional<Post> existingPost = postRepository.findByTitle(post.getTitle());
        if (existingPost.isPresent()) {
             throw new PostAlreadyExistsException("post Already exist");
        }

        return postRepository.save(post);
    }



    // get all the Post


    @Override
    public List<Post> getAllThePost() {
        List<Post> posts = postRepository.findAll();
        return posts;

    }


    // get specific Post
    @Override
    public Post getSpecificPost(Long id){
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("post with Id" + id + "Not found"));

    }

    // update the Post


    @Override
    public Post updateThePost(Long id, Post post) {
        Post existingPost = postRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not Found"));
                existingPost.setTitle(post.getTitle());
                existingPost.setContent(post.getContent());
                existingPost.setDate(post.getDate());
                return postRepository.save(existingPost);


    }



    // delete a post taking into account the comments


    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not Found"));

        // delete comments related to the post
        if (post.getComments() == null && post.getComments().isEmpty()) {
            commentRepository.deleteAll(post.getComments());
        }
        // delete the post
        postRepository.delete(post);
    }
}
