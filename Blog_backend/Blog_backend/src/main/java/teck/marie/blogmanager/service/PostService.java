package teck.marie.blogmanager.service;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import teck.marie.blogmanager.model.Post;

import java.util.List;

@Service
public interface PostService {
    Post creationOfpost(Post post);

    List<Post> getAllThePost();

    Post getSpecificPost(Long id);

    Post updateThePost(Long id, Post post);

    void deletePost(Long id);


}
