package org.example.jwsindividuellalf.controllers;

import org.example.jwsindividuellalf.entities.BlogPost;
import org.example.jwsindividuellalf.services.BlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class BlogController {

    private final BlogServiceImpl blogServiceImpl;

    @Autowired
    public BlogController(BlogServiceImpl blogServiceImpl){
        this.blogServiceImpl = blogServiceImpl;
    }

    @GetMapping("/posts")               //authenticated only
    public ResponseEntity<List<BlogPost>> getAllPosts() {
        return ResponseEntity.ok(blogServiceImpl.getAllPosts());
    }

    @GetMapping("/post/{id}")           //authenticated only
    public ResponseEntity<BlogPost> getPostById(@PathVariable("id") Long id){
        return ResponseEntity.ok(blogServiceImpl.getBlogById(id));
    }

    @GetMapping("/count")
    @PreAuthorize("hasRole('Blog_ADMIN')")//admin only
    public ResponseEntity<?> getBlogPostCount(JwtAuthenticationToken token){
        return blogServiceImpl.countBlogs(token);
    }

    @PostMapping("/newpost")
    @PreAuthorize("hasRole('Blog_USER')")//required role = user
    public ResponseEntity<BlogPost> createPost
            (@RequestBody BlogPost blogPost, JwtAuthenticationToken token){
        return new ResponseEntity<>(blogServiceImpl.addBlog(blogPost,token), HttpStatus.CREATED);
    }

    @PutMapping("/updatepost")
    @PreAuthorize("hasRole('Blog_USER')")//required role = user + owner of the blog post
    public ResponseEntity<?> updateBlog
            (@RequestBody BlogPost blogPostToUpdate, JwtAuthenticationToken token){
        return ResponseEntity.ok(blogServiceImpl.updateBlog(blogPostToUpdate,token));
    }

    @DeleteMapping("/deletepost/{id}")
    @PreAuthorize("hasAnyRole('Blog_ADMIN','Blog_USER')")//role required: admin or user + owner of the blog post
    public ResponseEntity<?> deletePostById(@PathVariable("id") Long id, JwtAuthenticationToken token){
        return blogServiceImpl.deleteBlogById(id, token);
    }


}

