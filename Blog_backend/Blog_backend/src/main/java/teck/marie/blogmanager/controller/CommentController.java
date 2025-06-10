package teck.marie.blogmanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teck.marie.blogmanager.model.Comment;
import teck.marie.blogmanager.service.CommentService;


@RestController
@RequestMapping(path = "/comment")
@Tag(name = "Blog Management API", description = "API allowing users to publish articles and add comment")
public class CommentController {

    // injection of Service

    private final CommentService commentService;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // method to add comments in Post
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/post/{postId}")
    @Operation(summary = "Add the comment in Post")
    public ResponseEntity<Comment> addCommentToPost(@PathVariable Long postId, @RequestBody Comment comment) {
        Comment savedComment = commentService.addCommentToPost(postId,comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
    }


    // Delete a comment in Post

    @DeleteMapping( "/post/{postId}/comment/{commentId}")
    @Operation(summary = "delete a comment in Post")
    public ResponseEntity<Void> deleteCommentFromPost(@PathVariable Long postId, @PathVariable Long commentId){
        commentService.DeleteCommentFromPost(postId,commentId);
        return ResponseEntity.noContent().build();
    }





}
