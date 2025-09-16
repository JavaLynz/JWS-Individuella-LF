package org.example.jwsindividuellalf.entities;

import jakarta.persistence.*;

@Entity
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length=50, nullable=false)
    private Long id;

    @Column(length=150, nullable=false)
    private String title;

    @Column(length=150, nullable=false)
    private String authorId;

    @Column(length=150, nullable=false)
    private String authorName;

    @Column(length=500, nullable=false)
    private String content;

    public BlogPost() {}

    public BlogPost(String title, String authorId, String authorName, String content) {
        this.title = title;
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorId() {
        return authorId;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
