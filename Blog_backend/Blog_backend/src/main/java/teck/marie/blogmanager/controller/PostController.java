package teck.marie.blogmanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teck.marie.blogmanager.model.Post;
import teck.marie.blogmanager.service.PostService;

import java.util.List;

@RestController
@RequestMapping(path = "/post")
@Tag(name = "Blog Management API", description = "API allowing users to publish articles and comments")
public class PostController {

    // injection of Service Layer
    private PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // create a Post

    @PostMapping()
    @Operation(summary = "create a post")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post) {
        Post createdPost = postService.creationOfpost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    //Read all the Post


    @GetMapping()
    @Operation(summary = "get All the posts")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllThePost());
    }


    // read a specific Post

    @GetMapping("/{id}")
    @Operation(summary = "get a specific Post by Id")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        return  ResponseEntity.ok(postService.getSpecificPost(id));
    }



    // Update a Post
    @PutMapping("/{id}")
    @Operation(summary = "updated  the post")
    public ResponseEntity<Post> updatePost( @PathVariable Long id, @RequestBody Post post) {
      Post updatedPost = postService.updateThePost(id, post);
      return ResponseEntity.ok(updatedPost);

    }



    // delete a Post
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "/{id}")
    @Operation(summary = "delete the Post taking the comment into account ")
    public ResponseEntity<Post> deletePostById(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok().build();
    }




}
