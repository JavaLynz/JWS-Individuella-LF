package org.example.jwsindividuellalf.services;

import org.example.jwsindividuellalf.entities.BlogPost;
import org.example.jwsindividuellalf.exceptions.ResourceNotFoundException;
import org.example.jwsindividuellalf.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public BlogPost addBlog(BlogPost blogPost, JwtAuthenticationToken token) {
        String userId = token.getToken().getSubject();
        String userName = token.getToken().getClaimAsString("preferred_username");

        blogPost.setAuthorId(userId);
        blogPost.setAuthorName(userName);

        System.out.println("Creating new blog post for user: " + userName + ", with Keycloak ID: " + blogPost.getAuthorId());

        return blogRepository.save(blogPost);
    }

    @Override
    public List<BlogPost> getAllPosts() {
        return blogRepository.findAll();
    }

    @Override
    public BlogPost getBlogById(Long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog Post","id",id));
    }

    @Override
    public ResponseEntity<?> updateBlog(BlogPost blogPost, JwtAuthenticationToken token) {
        String userId = token.getToken().getSubject();
        String userName = token.getToken().getClaimAsString("preferred_username");
        BlogPost existingBlogPost = blogRepository.findById(blogPost.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Blog Post","id",blogPost.getId()));

        if(!existingBlogPost.getAuthorId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("You are not the owner of this blog post.");
        }

        existingBlogPost.setTitle(blogPost.getTitle());
        existingBlogPost.setContent(blogPost.getContent());

        System.out.println(userName + ". Updated existing blog post: " + existingBlogPost );

        return ResponseEntity.ok(blogRepository.save(existingBlogPost));
    }

    @Override
    public ResponseEntity<?> deleteBlogById(Long id, JwtAuthenticationToken token) {

        String userId = token.getToken().getSubject();
        String userName = token.getToken().getClaimAsString("preferred_username");
        BlogPost potentialBlogToDelete = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog Post","id",id));

        System.out.println("blog post to delete: " + potentialBlogToDelete.toString());   //testing

        boolean isOwner = potentialBlogToDelete.getAuthorId().equals(userId);
        boolean isAdmin = token.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_Blog_ADMIN"));

        if (!isOwner && !isAdmin) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorised to delete this post.");
        }

        blogRepository.deleteById(potentialBlogToDelete.getId());
        System.out.println("Blog post with ID " + id + " deleted by user: " + userName + " (ID: " + userId + ")");

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> countBlogs(JwtAuthenticationToken token) {
        return ResponseEntity.ok(blogRepository.count()) ;
    }
}
