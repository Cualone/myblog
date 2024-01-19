package com.example.myBlog.posts.repository;

import com.example.myBlog.posts.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByTitleContaining(String title, Pageable pageable);
}
