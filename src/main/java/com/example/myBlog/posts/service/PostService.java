package com.example.myBlog.posts.service;

import com.example.myBlog.posts.entity.Post;
import com.example.myBlog.posts.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> findAllByTitle(String title, Pageable pageable) {
        List<Post> postList = postRepository.findAllByTitleContaining(title, pageable);
        return postList;

    }

    public List<Post> findAll() {
        List<Post> postList = postRepository.findAll();
        return postList;
    }

    @Transactional
    public void removeById(Long id) {
        Post findPost = getById(id);

        postRepository.delete(findPost);
    }

    @Transactional
    public void updateById(Long id, String title, String body) {

        Post findPost = getById(id);

        if (findPost == null) {
            return;
        }

        findPost.updatePost(title, body);
    }

    @Transactional
    public Long save(String title, String body) {

        Post save = postRepository.save(Post.createPost(title, body));

        return save.getId();
    }

    public Post getById(Long id) {
        Optional<Post> findPost = postRepository.findById(id);

        if (findPost.isPresent()) {
            return findPost.get();
        }
        return null;
    }



}
