INSERT INTO blog_post (id, title, author_id, author_name, content)
VALUES
    (1, 'First Blog Post', '400c6cb2-87ec-44be-a67d-4ab74e1469d1', 'OskarFox','This is the content of the first blog post.'),
    (2, 'Second Blog Post', '5c3a40d1-e5aa-462b-b5d9-374d351dcc07', 'GustavVasa','Second user post to test authorization.'),
    (3, 'Admin Announcement', '6f76e876-5c42-46c1-be55-95f477509dea', 'LynseyFox','This is a post created by an admin user.'),
    (4, 'Understanding JPA Relationships', '400c6cb2-87ec-44be-a67d-4ab74e1469d1', 'OskarFox', 'This post explores @OneToMany and @ManyToOne relationships in JPA with practical examples.'),
    (5, 'My Experience with Spring Boot', '5c3a40d1-e5aa-462b-b5d9-374d351dcc07', 'GustavVasa', 'Sharing my journey and challenges while developing my first Spring Boot application.'),
    (6, 'Admin Update: System Maintenance', '6f76e876-5c42-46c1-be55-95f477509dea', 'LynseyFox', 'A scheduled system maintenance will take place this weekend. Expect minor downtime.'),
    (7, 'Tips for Clean Code in Java', '400c6cb2-87ec-44be-a67d-4ab74e1469d1', 'OskarFox', 'Clean code is critical in team environments. Here are 10 tips to keep your codebase maintainable.'),
    (8, 'Authorization Testing Insights', '5c3a40d1-e5aa-462b-b5d9-374d351dcc07', 'GustavVasa', 'Here’s how I tested role-based access in a Spring Security setup.');
