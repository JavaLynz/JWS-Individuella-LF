package org.example.jwsindividuellalf.repositories;

import org.example.jwsindividuellalf.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<BlogPost, Long> {

    Optional<BlogPost> findByAuthorId(String authorId);
}
