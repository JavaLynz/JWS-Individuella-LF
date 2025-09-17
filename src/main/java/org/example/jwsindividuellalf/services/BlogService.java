package org.example.jwsindividuellalf.services;

import org.example.jwsindividuellalf.entities.BlogPost;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;

public interface BlogService {

    public BlogPost addBlog(BlogPost blogPost, JwtAuthenticationToken token);

    public List<BlogPost> getAllPosts();

    public BlogPost getBlogById(Long id);

    public ResponseEntity<?> updateBlog(BlogPost blogPost, JwtAuthenticationToken token);

    public ResponseEntity<?> deleteBlogById(Long id, JwtAuthenticationToken token);

    public ResponseEntity<?> countBlogs();


}
