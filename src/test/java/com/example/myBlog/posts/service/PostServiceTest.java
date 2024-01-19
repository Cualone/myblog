package com.example.myBlog.posts.service;

import com.example.myBlog.posts.entity.Post;
import com.example.myBlog.posts.repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class PostServiceTest {

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    //1. 포스트 생성
    //2. 포스트 삭제
    //3. 포스트 목록
    //4. 포스트 검색
    //5. 포스트 수정

    @Test
    @DisplayName("포스트 생성")
    public void postCreateTest() {

        //given


        //when
        Long savedId = postService.save("title1", "body1");
        //then
        assertThat(savedId).isEqualTo(1L);
    }

    @Test
    @DisplayName("포스트 삭제")
    public void deleteTest() {
        //given
        Long savedId = postService.save("title1", "body1");
        //when
        postService.removeById(savedId);

        Post findPost = postService.getById(savedId);
        //then
        assertThat(findPost).isNull();
    }

    @Test
    @DisplayName("포스트 목록")
    public void listTest() {
        //given
        for (int i = 0; i < 5; i++) {
            postService.save("title" + i, "body" + i);
        }
        //when
        List<Post> postList = postService.findAll();
        //then
        assertThat(postList.size()).isEqualTo(5);
    }

    @Test
    @DisplayName("포스트 검색")
    public void searchTest() {
        //given
        for (int i = 0; i < 5; i++) {
            if(i%2==0)
                postService.save("title a", "body");
            else
                postService.save("title b" + i, "body");
        }
        //when
        List<Post> post = postService.findAllByTitle("a", PageRequest.of(0, 3));

        //then
        assertThat(post.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("게시물 수정 테스트")
    public void updateTest() {

        //given
        Long savedId = postService.save("title", "body");

        //when
        postService.updateById(savedId, "updated title", "updated body");

        Post findPost = postService.getById(savedId);

        //then
        assertThat(findPost.getTitle()).isEqualTo("updated title");
    }
}